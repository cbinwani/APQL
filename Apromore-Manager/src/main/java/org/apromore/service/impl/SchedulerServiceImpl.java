/*
 * Copyright © 2009-2014 The Apromore Initiative.
 *
 * This file is part of "Apromore".
 *
 * "Apromore" is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * "Apromore" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.
 * If not, see <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */

package org.apromore.service.impl;

import org.apromore.exception.SchedulerException;
import org.apromore.scheduler.Worker;
import org.apromore.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Responsible for running the scheduled tasks.
 * All scheduled tasks that are run by Apromore at a regular interval must be implemented here.
 *
 * @author Cameron James
 * @since 1.0
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {

    protected static Logger LOGGER = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    private Worker metricWorker;
    private Worker gedMatrixWorker;


    /**
     * Default Constructor.
     * @param metWorker the Metric Worker for the metric Calculations.
     */
    @Inject
    public SchedulerServiceImpl(final @Qualifier("metricWorker") Worker metWorker, final @Qualifier("gedMatrixWorker") Worker gedWorker) {
        metricWorker = metWorker;
        gedMatrixWorker = gedWorker;
    }



    /**
     * @see org.apromore.service.SchedulerService#runMetricCalculations()
     * {@inheritDoc}
     */
    @Override
    @Scheduled(cron = "0 0 21 * * *")
    public void runMetricCalculations() {
        LOGGER.info("Starting Execution of the Metric Calculation Job!");

        try {
            metricWorker.executeJob();
        } catch(SchedulerException se) {
            LOGGER.error("Scheduled Metric Calculation Run Failed", se);
        }

        LOGGER.info("Metric Calculation Job Completed!");
    }


    /**
     * @see org.apromore.service.SchedulerService#runGEDMatrixComputation()
     * {@inheritDoc}
     */
    @Override
    @Scheduled(cron = "0 0 22 * * FRI")
    public void runGEDMatrixComputation() {
        LOGGER.info("Starting Execution of the GED Matrix Computation!");

        try {
            gedMatrixWorker.executeJob();
        } catch(SchedulerException se) {
            LOGGER.error("GED Matrix Computation Run Failed", se);
        }

        LOGGER.info("GED Matrix Computation Completed!");
    }

}
