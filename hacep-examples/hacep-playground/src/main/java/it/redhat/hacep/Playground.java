/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.redhat.hacep;

import it.redhat.hacep.configuration.HACEPApplication;
import it.redhat.hacep.console.TextUI;
import it.redhat.hacep.console.UI;
import it.redhat.hacep.console.commands.*;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Playground {

    private static Logger log = LoggerFactory.getLogger(Playground.class.getName());

    private final HACEPApplication application;

    public Playground(HACEPApplication application) {
        this.application = application;
    }

    public void start() throws Exception {
        application.start();
        UI textUI = new TextUI(System.in, System.out);
        List<ConsoleCommand> uiCommands = baseCommands(application);
        uiCommands.forEach(textUI::register);
        printBanner();
        textUI.start();
    }

    private void printBanner() {
        System.out.println("---------------------------------------");
        System.out.println("                HACEP CLI");
        System.out.println("---------------------------------------");
        System.out.println();
    }

    private List<ConsoleCommand> baseCommands(HACEPApplication hacepApplication) {
        return Arrays.asList(
                new AddressConsoleCommand(hacepApplication),
                new GetConsoleCommand(hacepApplication),
                new HelpConsoleCommand(),
                new InfoConsoleCommand(hacepApplication),
                new AllConsoleCommand(hacepApplication),
                new LocalConsoleCommand(hacepApplication),
                new PrimaryConsoleCommand(hacepApplication),
                new ReplicaConsoleCommand(hacepApplication),
                new QuitConsoleCommand(hacepApplication));
    }

}