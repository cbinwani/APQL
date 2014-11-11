package org.apromore.service.impl;

import org.apromore.service.LolaDirBean;
import org.apromore.service.PqlBean;
import org.apromore.service.ProcessService;
import org.jbpt.petri.persist.PetriNetMySQL;
import org.pql.api.IPQLAPI;
import org.pql.api.PQLAPI;
import org.pql.core.PQLBasicPredicates;
import org.pql.label.LabelManagerVSM;
import org.pql.logic.IThreeValuedLogic;
import org.pql.logic.KleeneLogic;
import org.pql.mc.LoLAModelChecker;
import org.pql.persist.PQLBasicPredicatesMySQL;
import org.pql.persist.PQLMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by corno on 23/08/2014.
 */
public class PqlBeanImpl implements PqlBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(PqlBeanImpl.class);

    private final Set<Double> indexedLabelSimilarities = new HashSet<Double>();
    private final double defaultLabelSimilarity = 0.75;

    private LoLAModelChecker lolaModelChecker;
    private LabelManagerVSM labelMngr;
    private PQLBasicPredicates basicPredicatesLoLA;
    private PQLBasicPredicatesMySQL basicPredicatesMySQL;
    private PQLMySQL pqlMySQL;
    private PetriNetMySQL pnMySQL;
    private IThreeValuedLogic logic;

    private IPQLAPI pqlAPI;

    private LolaDirBean lolaDir;
    private MySqlBeanImpl mySqlBean;
    private PGBeanImpl pgBean;

//    public PqlBeanImpl(){
//    }

    @Inject
    public PqlBeanImpl(LolaDirImpl lolaDir, MySqlBeanImpl mySqlBean, PGBeanImpl pgBean){
        this.lolaDir = lolaDir;
        this.mySqlBean=mySqlBean;
        this.pgBean=pgBean;

        indexedLabelSimilarities.add(new Double(0.5));
        indexedLabelSimilarities.add(new Double(0.75));
        indexedLabelSimilarities.add(new Double(1.0));
    }

    @Override
    public IPQLAPI getApi() {
        try {
            logic = new KleeneLogic();
            lolaModelChecker = new LoLAModelChecker(lolaDir.getLolaDir());
            labelMngr = new LabelManagerVSM(mySqlBean.getURL(), mySqlBean.getUser(), mySqlBean.getPassword(),
                    pgBean.getHost(), pgBean.getName(), pgBean.getUser(), pgBean.getPassword(), defaultLabelSimilarity,
                    indexedLabelSimilarities);
            pnMySQL = new PetriNetMySQL(mySqlBean.getURL(), mySqlBean.getUser(), mySqlBean.getPassword());

            basicPredicatesMySQL = new PQLBasicPredicatesMySQL(mySqlBean.getURL(), mySqlBean.getUser(), mySqlBean.getPassword(), labelMngr);
            basicPredicatesLoLA = new PQLBasicPredicates(lolaModelChecker);
            pqlMySQL = new PQLMySQL(mySqlBean.getURL(), mySqlBean.getUser(), mySqlBean.getPassword(), labelMngr,
                    basicPredicatesLoLA);

            pqlAPI = new PQLAPI(lolaModelChecker, logic, pnMySQL, pqlMySQL,
                    basicPredicatesMySQL, labelMngr);
        }catch(Exception ex){
            LOGGER.error("------------------" + ex.toString());
        }
        return pqlAPI;
    }

}
