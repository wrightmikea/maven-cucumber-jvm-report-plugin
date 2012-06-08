package info.cukes.report;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import net.masterthought.jenkins.FeatureReportGenerator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Goal which generates a Cucumber Report.
 * @goal generate
 * @phase verify
 */
public class CucumberReportGeneratorMojo extends AbstractMojo {
   
   /**
    * Name of the project.
    * @parameter expression="${project.name}"
    * @required
    */
   private String projectName;
   
   /**
    * Location of the file.
    * @parameter expression="${project.build.directory}/cucumber-reports"
    * @required
    */
   private File outputDirectory;
   
   /**
    * Location of the file.
    * @parameter expression="${project.build.directory}/cucumber.json"
    * @required
    */
   private File cucumberOutput;

   public void execute() throws MojoExecutionException {
      if (!outputDirectory.exists()) {
         outputDirectory.mkdirs();
      }
      
      List<String> list = new ArrayList<String>();
      list.add(cucumberOutput.getAbsolutePath());
      FeatureReportGenerator featureReportGenerator;
      try {
         featureReportGenerator = new FeatureReportGenerator(list, outputDirectory, "", null, projectName, false, true);
         featureReportGenerator.generateReports();
      } catch (Exception e) {
         throw new MojoExecutionException("Could not create report.", e);
      }
   }
}
