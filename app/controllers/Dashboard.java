package controllers;

import models.Member;
import models.Assessment;
import models.Trainer;
import play.Logger;
import play.db.jpa.JPABase;
import play.mvc.Controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessments = member.assessments;
    //String bmi = new DecimalFormat("#00.00").format(gymUtility.calculatedBMI(Accounts.getLoggedInMember(),member.assessments.get(0)));
    double BMI = 0;
    if (assessments.size() > 0) {
      BMI = gymUtility.calculatedBMI(member, assessments.get(0));
    }
    boolean isIdealBodyWeight = true;
    if (assessments.size()>0){
     isIdealBodyWeight = gymUtility.isIdealBodyWeight(member, assessments.get(0));
  }
    String determineBMICategory = gymUtility.determineBMICategory(BMI);
    render("dashboard.html", member, assessments, BMI, determineBMICategory, isIdealBodyWeight);
  }

  public static void indexTrainer() {
    Trainer trainer = Accounts.getLoggedInTrainer();
    List<Member> member = Member.findAll();
    render("trainerDashboard.html", member,trainer);
  }
  public static void trainerMemberIndex(Long id) {
    Logger.info("Rendering Member - Trainer View");
    Member member = Member.findById(id);
    List<Assessment> assessments = member.assessments;
    render ("trainerAssessmentView.html", member,assessments);
  }
  public static void deleteAssessment(Long assessmentid)
  {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = Assessment.findById(assessmentid);
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting" + assessment.id);
    redirect("/dashboard");
  }

  public static void addAssessment(double weight,double chest,double thigh,
                                   double upperArm, double waist, double hips, double height, double bmi,String comment)
  {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(weight,chest,thigh,upperArm,waist,hips,comment);
    member.assessments.add(0,assessment);
    member.save();
    Logger.info("Adding Assessment" );
    redirect("/dashboard" );
  }





  /*public static void deleteAssessment(Long assessmentid) {
    Assessment assessment = Assessment.findById(assessmentid);
    Member member = Accounts.getLoggedInMember();
    member.save();
    assessment.delete();
    Logger.info("Deleting " + assessmentid);
    redirect("/dashboard");
  }*/

}
