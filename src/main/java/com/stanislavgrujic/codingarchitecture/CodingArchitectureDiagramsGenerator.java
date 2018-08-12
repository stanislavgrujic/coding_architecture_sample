package com.stanislavgrujic.codingarchitecture;

import com.structurizr.Workspace;
import com.structurizr.io.plantuml.PlantUMLWriter;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.view.Shape;
import com.structurizr.view.Styles;
import com.structurizr.view.SystemContextView;
import com.structurizr.view.ViewSet;

import java.io.FileWriter;
import java.io.IOException;

public class CodingArchitectureDiagramsGenerator {

  public static void main(String[] args) throws IOException {
    Workspace workspace = new Workspace("Coding Architecture Sample", "This is a sample C4 generation algorithm");
    Model model = workspace.getModel();

    Person user = model.addPerson("Execom Employee", "Execom's employee");
    SoftwareSystem softwareSystem = model.addSoftwareSystem("Test generator", "Application for automated generation of tests for hiring interviews.");
    user.uses(softwareSystem, "Creates formulas on");

    ViewSet views = workspace.getViews();
    SystemContextView contextView = views.createSystemContextView(softwareSystem, "SystemContext", "System Context for Test Generator application");
    contextView.addAllPeople();
    contextView.addAllSoftwareSystems();

    Styles styles = views.getConfiguration().getStyles();
    styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
    styles.addElementStyle(Tags.PERSON).background("#08427b").color("#ffffff").shape(Shape.Person);

    try (FileWriter fileWriter = new FileWriter("plantuml/test.puml")) {
      PlantUMLWriter writer = new PlantUMLWriter();
      writer.write(workspace, fileWriter);
    }

  }
}
