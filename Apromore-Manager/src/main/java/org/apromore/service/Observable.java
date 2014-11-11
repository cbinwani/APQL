package org.apromore.service;

import org.apromore.dao.model.ProcessModelVersion;
import org.apromore.dao.model.User;
import org.apromore.dao.model.NativeType;
import org.apromore.dao.model.Process;
import org.apromore.helper.Version;

/**
 * Created by corno on 5/07/2014.
 */
public interface Observable {
    void addObserver(Observer ob);

    void removeObserver(Observer ob);

    void notifyObserver(User user, NativeType nativeType,ProcessModelVersion pmv, boolean delete);
}
