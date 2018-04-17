/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.CompteBancaire.managebeans;

import ht.mbds.comptebancaire.entities.Utilisateurs;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author saul
 */
@Named(value = "dashBoardMBean")
@ViewScoped
public class DashBoardMBean implements Serializable {

    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private LineChartModel dateModel;
    private boolean loginSuccess;

    public void preRenderView() throws IOException{

        System.out.println("test");
        FacesContext facesContext = FacesContext.getCurrentInstance();
       HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Utilisateurs user = (Utilisateurs) session.getAttribute("user");
        if (user == null) {
             ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.redirect(ec.getRequestContextPath() + "/signin.xhtml");
            
          
  
}
        createPieModels();

    }

    public LineChartModel getDateModel() {
        return dateModel;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    private void createPieModels() {
        createPieModel1();
        createPieModel2();
        createDateModel();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();

        pieModel1.set("Epagne", 540);
        pieModel1.set("Conjoint", 325);
        pieModel1.set("Public", 702);
        pieModel1.set("Autre", 421);

        pieModel1.setTitle("Type de compte");
        pieModel1.setLegendPosition("w");
    }

    private void createPieModel2() {
        pieModel2 = new PieChartModel();

        pieModel2.set("Brand 1", 540);
        pieModel2.set("Brand 2", 325);
        pieModel2.set("Brand 3", 702);
        pieModel2.set("Brand 4", 421);

        pieModel2.setTitle("Custom Pie");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }

    private void createDateModel() {
        dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
        series1.set("2014-01-01", 51);
        series1.set("2014-01-06", 22);
        series1.set("2014-01-12", 65);
        series1.set("2014-01-18", 74);
        series1.set("2014-01-24", 24);
        series1.set("2014-01-30", 51);
        dateModel.addSeries(series1);
        dateModel.setTitle("Transaction par date");
        dateModel.setZoom(true);
        dateModel.getAxis(AxisType.Y).setLabel("Quantite");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setMax("2014-02-01");
        axis.setTickFormat("%b %#d, %y");
        dateModel.getAxes().put(AxisType.X, axis);
    }
}
