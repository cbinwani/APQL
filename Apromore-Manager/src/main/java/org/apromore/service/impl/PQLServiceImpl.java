package org.apromore.service.impl;

import org.apache.commons.io.IOUtils;
import org.apromore.canoniser.Canoniser;
import org.apromore.canoniser.provider.CanoniserProvider;
import org.apromore.dao.ProcessModelVersionRepository;
import org.apromore.dao.dataObject.FolderTreeNode;
import org.apromore.dao.model.*;
import org.apromore.dao.model.Process;

import org.apromore.helper.Version;
import org.apromore.model.*;
import org.apromore.plugin.property.RequestParameterType;
import org.apromore.service.*;
import org.apromore.service.PQLService;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;

import org.apromore.service.helper.UserInterfaceHelper;
import org.jbpt.petri.INetSystem;
import org.pql.api.IPQLAPI;
import org.pql.core.PQLTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.pql.api.MyIndex;

import javax.activation.DataHandler;
import javax.inject.Inject;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by corno on 22/07/2014.
 */
@Service
public class PQLServiceImpl implements PQLService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PQLServiceImpl.class);

    private final String admin = "admin";
    private String PNMLCanoniser;
    @Inject
    private WorkspaceService workspaceService;
    @Inject
    private ProcessService processService;
    @Inject
    private PluginService pluginService;
    @Inject
    private CanoniserService canoniserService;
    @Inject
    private UserService userService;
    @Inject
    private UserInterfaceHelper helperService;
    @Inject
    private CanoniserProvider canoniserProviderService;
    @Inject
    private ProcessModelVersionRepository processModelVersionRepository;

    private String parameterCategory = Canoniser.DECANONISE_PARAMETER;
    private MyIndex index;

    private final Set<Double> indexedLabelSimilarities = new HashSet<Double>();
//    private final double defaultLabelSimilarity = 0.75;
//    private static final String OS = System.getProperty("os.name").toLowerCase();

//    private LoLAModelChecker lolaModelChecker;
//    private LabelManagerVSM labelMngr;
//    private PQLBasicPredicates basicPredicatesLoLA;
//    private PQLBasicPredicatesMySQL basicPredicatesMySQL;
//    private PQLMySQL pqlMySQL;
//    private PetriNetMySQL pnMySQL;
//    private IThreeValuedLogic logic;

//    private final String mysqlURL = "jdbc:mysql://127.0.0.1:3306/mysql";
//    private final String mysqlUser = "root";
//    private final String mysqlPassword = "MAcri";

//    private final String pgHost = "localhost";
//    private final String pgName = "vsm";
//    private final String pgUser = "postgres";
//    private final String pgPassword = "password";

//    private IPQLAPI pqlAPI;

    private LolaDirBean lolaDir;
    private MySqlBeanImpl mySqlBean;
    private PGBeanImpl pgBean;
    private LinkedList<Thread> queue;

    private Map<PQLTask,PQLTask> map;
    private PqlBeanImpl pqlBean;
//    private Lock lock=new ReentrantLock();
//    private Condition condition=lock.newCondition();
    private AtomicInteger count=new AtomicInteger(0);
    private int numberOfCore = Runtime.getRuntime().availableProcessors();
    private Semaphore sem= new Semaphore(numberOfCore-1);

//    public PQLServiceImpl() {
//    }

    @Inject
    public PQLServiceImpl(LolaDirImpl lolaDir, MySqlBeanImpl mySqlBean, PGBeanImpl pgBean, PqlBeanImpl pqlBean) {
        this.lolaDir = lolaDir;
        this.mySqlBean=mySqlBean;
        this.pgBean=pgBean;
        this.pqlBean=pqlBean;
        indexedLabelSimilarities.add(new Double(0.5));
        indexedLabelSimilarities.add(new Double(0.75));
        indexedLabelSimilarities.add(new Double(1.0));
        queue=new LinkedList<>();
    }



    @Override
    public void indexAllModels() {
        for(Canoniser canoniser : canoniserProviderService.listAll()){
            if(canoniser.getNativeType().startsWith("PNML"))
                PNMLCanoniser=canoniser.getNativeType();
        }

        LinkedList<FolderTreeNode> root = null;
        LinkedList<GroupProcess> processes = new LinkedList<>();

        try {
            String userID = userService.findUserByLogin(admin).getRowGuid();
            root = new LinkedList<>(workspaceService.getWorkspaceFolderTree(userID));

            FolderTreeNode head;
            Integer folderId = 0;

            processes.addAll(workspaceService.getGroupProcesses(userID, folderId));
            indexModels(processes, folderId, userID);
            processes.clear();

            while (!root.isEmpty()) {
                head = root.removeFirst();
                folderId = head.getId();
                processes.addAll(workspaceService.getGroupProcesses(userID, folderId));
                indexModels(processes, folderId, userID);
                root.addAll(head.getSubFolders());
                processes.clear();
            }

        } catch (Exception e) {
            for (StackTraceElement ste : e.getStackTrace())
                LOGGER.info("ERRORE: " + e.getMessage() + ste.getClassName() + " " + ste.getMethodName() + " " + ste.getLineNumber() + " " + ste.getFileName());
        }
    }

    @Override
    public void indexOneModel(ProcessModelVersion pmv) {

        Process process=pmv.getProcessBranch().getProcess();
        Version version2=new Version(pmv.getVersionNumber());
        Set<RequestParameterType<?>> canoniserProperties=readPluginProperties(parameterCategory);
        for(Canoniser canoniser : canoniserProviderService.listAll()){
            if(canoniser.getNativeType().startsWith("PNML"))
                PNMLCanoniser=canoniser.getNativeType();
        }
        PqlBean pqlBean= new PqlBeanImpl((LolaDirImpl)lolaDir,mySqlBean,pgBean);
        IPQLAPI api=pqlBean.getApi();

        try {
//            LOGGER.error("PQLSERVICE: Dati proc "+processname+" "+processId+" "+branch+" "+version+" "+PNMLCanoniser+" nulla false"+canoniserProperties);
            ExportFormatResultType exportResult = this.processService.exportProcess(process.getName(), process.getId(), pmv.getProcessBranch().getBranchName(), version2, PNMLCanoniser, PNMLCanoniser, false, canoniserProperties);

            InputStream input = exportResult.getNative().getInputStream();
            InputStream input2= exportResult.getNative().getInputStream();
            Scanner sc=new Scanner(input2);
            PrintWriter pw=new PrintWriter(new FileWriter("C:/Users/corno/net/"+pmv.getProcessBranch().getProcess().getName()+".txt"));
            while(sc.hasNextLine()){
                pw.println(sc.nextLine());
            }
            pw.close();
            byte[] bytes = IOUtils.toByteArray(input);

            INetSystem netSystem = api.bytes2NetSystem(bytes);

            int pi,ti;
            pi = ti = 1;
            for (Place p : (Set<Place>)netSystem.getPlaces())
                p.setName("p"+pi++);
            for (Transition t : (Set<Transition>)netSystem.getTransitions()) {
                t.setName("t" + ti++);
                t.setLabel(t.getLabel().replaceAll("[\n]"," "));
            }

            netSystem.loadNaturalMarking();


            if (api.checkNetSystem(netSystem)) {
//                LOGGER.info("SOUNDNESS: " + true);
                api.indexNetSystem(netSystem, process.getId() + "/" + version2.toString() + "/" + pmv.getProcessBranch().getBranchName(), indexedLabelSimilarities);
//                LOGGER.error("--------------------------------------------------------INDEXED: "+process.getName());
            }
//            else{
//                LOGGER.info("SOUNDNESS: " + false);
//            }
        }catch(Exception e){
            LOGGER.error("-----------ERRORRE: " + e.toString());
            for (StackTraceElement ste : e.getStackTrace())
                LOGGER.info("ERRORE: " + ste.getClassName() + " " + ste.getMethodName() + " " + ste.getLineNumber() + " " + ste.getFileName());
        }
    }

    @Override
    public void deleteModel(ProcessModelVersion pmv) {
        Version version=new Version(pmv.getVersionNumber());
        Process process = pmv.getProcessBranch().getProcess();
        try {
            PqlBean pqlBean= new PqlBeanImpl((LolaDirImpl)lolaDir,mySqlBean,pgBean);
            IPQLAPI api=pqlBean.getApi();
//            LOGGER.error("-----------DELETE: " + pmv.getProcessBranch().getProcess().getId()+"/"+version.toString()+"/"+pmv.getProcessBranch().getBranchName());
            api.deleteNetSystem(pmv.getProcessBranch().getProcess().getId() + "/" + version.toString() + "/" + pmv.getProcessBranch().getBranchName());
        }catch(Exception e){
            LOGGER.error("-----------ERRORRE: " + e.toString());
            for (StackTraceElement ste : e.getStackTrace())
                LOGGER.info("ERRORE: " + ste.getClassName() + " " + ste.getMethodName() + " " + ste.getLineNumber() + " " + ste.getFileName());
        }
    }

    @Override
    public void update(User user, NativeType nativeType,final ProcessModelVersion pmv, final boolean delete) {
//            LOGGER.error("-----------lock: " + pmv.getProcessBranch().getProcess().getName());
//            lock.lock();

            Runnable run = new Runnable() {
                @Override
                public void run() {
//                    while(count.get() > numberOfCore - 1) {
//                            try {
//                                PQLServiceImpl.this.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                    }
                    try {
                        PQLServiceImpl.this.sem.acquire();
                        if (!delete) {
                            indexOneModel(pmv);
                        } else {
//                            LOGGER.error("--------------------------------------------------------DELETED: " + pmv.getProcessBranch().getProcess().getName());
                            deleteModel(pmv);
                        }

                        count.decrementAndGet();
                        queue.remove(Thread.currentThread());
//                        LOGGER.error("-----------COUNT: " + count.get() + " ");
                        PQLServiceImpl.this.sem.release();
                    }catch(InterruptedException ie){

                    }
                }
            };
            Thread thread = new Thread(run);
//            queue.addLast(thread);
//            count.incrementAndGet();
//            LOGGER.error("-----------COUNTBEFOREWHILE: " + count.get()+" ");

//            if(count.get() > numberOfCore - 1) {
//                LOGGER.error("-----------if: " + count.get()+" ");
//                condition.await();
//            }
            thread.start();
    }

    private Set<RequestParameterType<?>> readPluginProperties(String parameterCategory) {
        Set<RequestParameterType<?>> requestProperties = new HashSet<>();
        requestProperties.add(new RequestParameterType<>("isCpfTaskPnmlTransition",true));
        requestProperties.add(new RequestParameterType<>("isCpfTaskPnmlTrans",false));
        try {
        } catch (Exception e) {
            LOGGER.error("-----------ERRORRE PluginProperties: " + e.toString());
            for (StackTraceElement ste : e.getStackTrace())
                LOGGER.info("ERRORE: " + ste.getClassName() + " " + ste.getMethodName() + " " + ste.getLineNumber() + " " + ste.getFileName());
        }

        return requestProperties;
    }

    private void indexModels(LinkedList<GroupProcess> processes, Integer folderId, String userID) {
        Process currentProc;
        String procName;
        Integer procId;
        Version version = null;
        String nativeType;
        String annotationName = null;
        boolean withAnnotation = false;

        IPQLAPI api=pqlBean.getApi();
//        LOGGER.error("-----------PQLAPI: " + api);

        Set<RequestParameterType<?>> canoniserProperties = null;
        try {
            for (GroupProcess process : processes) {
                currentProc = process.getProcess();
                procName = currentProc.getName();
                procId = currentProc.getId();
                nativeType = currentProc.getNativeType().getNatType();
                canoniserProperties = readPluginProperties(parameterCategory);

                for (ProcessSummaryType pst : helperService.buildProcessSummaryList(userID, folderId, null).getProcessSummary()) {
                    if (pst.getName().equals(procName)) {
                    for (VersionSummaryType vst : pst.getVersionSummaries()) {

                            version = new Version(vst.getVersionNumber());
                            if (version != null && canoniserProperties != null) {
                                LOGGER.info("PROCESS: " + procName + " " + procId + " " + vst.getName() + " " + version.toString() + " " + nativeType);
                                ExportFormatResultType exportResult = this.processService.exportProcess(procName, procId, vst.getName(), version, PNMLCanoniser, annotationName, withAnnotation, canoniserProperties);
                                DataHandler data = exportResult.getNative();

                                InputStream input = data.getInputStream();
                                byte[] bytes = IOUtils.toByteArray(input);

                                INetSystem netSystem = api.bytes2NetSystem(bytes);

                                int pi,ti;
                                pi = ti = 1;
                                for (Place p : (Set<Place>)netSystem.getPlaces())
                                    p.setName("p"+pi++);
                                for (Transition t : (Set<Transition>)netSystem.getTransitions())
                                    t.setName("t"+ti++);

                                netSystem.loadNaturalMarking();

//                                LOGGER.info("SOUNDNESS: " + api.checkNetSystem(netSystem));
                                if (api.checkNetSystem(netSystem)) {
//                                    LOGGER.error("INDEX: " + );
                                    api.indexNetSystem(netSystem, procId.toString() + "/" + vst.getVersionNumber() + "/" + vst.getName(), indexedLabelSimilarities);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("-----------ERRORRE: " + e.toString());
            for (StackTraceElement ste : e.getStackTrace())
                LOGGER.info("ERRORE: " + ste.getClassName() + " " + ste.getMethodName() + " " + ste.getLineNumber() + " " + ste.getFileName());
        }
    }

    @Override
    public List<String> runAPQLQuery(String queryPQL, List<String> IDs, String userID) {
        Set<String> idNets=new HashSet<>();
        List<String> results=new LinkedList<>();
        IPQLAPI api=pqlBean.getApi();
//        LOGGER.error("-----------PQLAPI: " + api);
        try {
            api.prepareQuery(queryPQL);
            if (api.getLastNumberOfParseErrors() != 0) {
                results = api.getLastParseErrorMessages();
            } else {//risultati
//                LOGGER.error("-----------IDS PQLServiceImpl" + IDs);
                map=api.getLastQuery().getTaskMap();
                LinkedList<PQLTask> tasks=new LinkedList<>(map.values());

                idNets=new HashSet<>(IDs);
                idNets=api.checkLastQuery(idNets);
                results.addAll(idNets);
//                LOGGER.error("-----------QUERYAPQL ESATTA "+results);
            }

        } catch (Exception e) {
            LOGGER.error("-----------ERRORRE: " + e.toString());
            for (StackTraceElement ste : e.getStackTrace())
                LOGGER.info("ERRORE: " + ste.getClassName() + " " + ste.getMethodName() + " " + ste.getLineNumber() + " " + ste.getFileName());
        }
        return results;
    }

    @Override
    public List<Detail> getDetails(){
        List<Detail> details = new LinkedList<>();
        Detail detail;
        for(PQLTask task : map.keySet()){
            PQLTask taskTwo = map.get(task);

            detail= new Detail();
            detail.setLabelOne(task.getLabel());
            detail.setSimilarityLabelOne(""+task.getSimilarity());
            detail.getDetail().addAll(taskTwo.getSimilarLabels());
            details.add(detail);
        }
        return details;
    }

}
