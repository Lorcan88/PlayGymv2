package controllers;
import models.Member;
import models.Assessment;
import play.mvc.Controller;

public class gymUtility extends Controller {


    public static double calculatedBMI(Member member, Assessment assessment) {
        double bmiValue;

        if (member.assessments.size() != 0) {
            bmiValue = (assessment.weight) / (member.height * member.height);
        } else {
            bmiValue = 0.0;
        }
        return bmiValue;
    }



    public static String determineBMICategory(double bmiValue){

        if (bmiValue < 16){
            return "SEVERELY UNDERWEIGHT";
        }
        if (bmiValue >= 16 && bmiValue <= 18.5){
            return "UNDERWEIGHT";
        }
        if (bmiValue >=18.5 && bmiValue <= 25){
            return "NORMAL";
        }
        if (bmiValue >= 25 && bmiValue <= 30){
            return "OVERWEIGHT";
        }
        if (bmiValue >= 30 && bmiValue <= 35){
            return "MODERATELY OBESE";
        }
        if (bmiValue > 35){
            return "SEVERELY OBESE";
        }
        return null;
    }

    /*public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        double height = member.height*39.37f;
        double weight = assessment.weight;
        double idealBodyWeight = 10;
        boolean isIdealBodyWeight = false;
        if (member.gender.equals("M"))
        {
            idealBodyWeight = 50.0f + 0.9f * (height - 152.0f);
        }
        if (member.gender.equals("F") || member.gender.equals("Unspecified"))
        {
            idealBodyWeight = 45.5f + 0.9f * (height - 152.0f);
        }
        if(idealBodyWeight == weight)
        {
            isIdealBodyWeight = true;
        }
        return isIdealBodyWeight;

    }*/

    /*public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        double idealWeight=10;
        double meterToInches = member.height;

        if (member.gender.equals("m")) {
            idealWeight = 50.0 + 0.9 * ((member.height/100)-152);


        } else if (member.gender.equals("F") || member.gender.equals("Unspecified")){
            idealWeight = 45.5 + 0.9 * ((member.height/100)-152);
            return true;
        }
        if(idealWeight == assessment.weight)
        {
            isIdealBodyWeight = true;
        }
        return isIdealBodyWeight;
        //return (idealWeight >= -0.2 && idealWeight <= 0.2);
        }*/

    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        double weight;
        double idealBodyWeight= 50;
        boolean isIdealBodyWeight = false;
        if(assessment.weight<=0){
            weight=member.startingWeight;
        } else {
            weight=assessment.weight;
        }
        if(member.gender.equals("Male")){
            idealBodyWeight=50.0+2.3*((member.height*39.3701)-60);
        }
        if(member.gender.equals("Female")||member.gender.equals("Unspecified")){
            idealBodyWeight=45.5+2.3*((member.height*39.3701)-60);
        }
        if((idealBodyWeight<=(weight+0.2))&&(idealBodyWeight>=(weight-0.2))){
            isIdealBodyWeight=true;
        }
        return isIdealBodyWeight;
    }








}
