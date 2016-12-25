/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculation;

public class RoundOff {
    private double afterRoundOffValue;
    private String roundOffValue;
    
    public RoundOff(double value){
        afterRoundOffValue = calculateMethod(value);
    }
    
    private double calculateMethod(double value){
        StringBuilder newValue = new StringBuilder();
        StringBuilder roundOffValueTemp = new StringBuilder("-0.00");
        
        String valueTemp = String.format("%.2f", value);
        int start=0;
        for(int i =0; i<valueTemp.length();i++){
             if(valueTemp.charAt(i)=='.'){
                 start = i;
                 start++;
                 break;
             }
        }
       
        int beforeDotValue = Integer.parseInt(valueTemp.substring(0, start-1));
        valueTemp = valueTemp.substring(start);
        int firstValue= Integer.parseInt(""+valueTemp.charAt(0));
        int secondValue;
        try{
            valueTemp = valueTemp.substring(0,2);
            secondValue= Integer.parseInt(""+valueTemp.charAt(1));

            if(secondValue==1||secondValue==2){
                if(secondValue==1){
                    roundOffValueTemp.delete(0, roundOffValueTemp.length());
                    roundOffValueTemp.append("-0.01");
                }
                else{
                    roundOffValueTemp.delete(0, roundOffValueTemp.length());
                    roundOffValueTemp.append("-0.02");
                }
                secondValue = 0;
            }else if(secondValue==3||secondValue==4){
                if(secondValue==3){
                    roundOffValueTemp.delete(0, roundOffValueTemp.length());
                    roundOffValueTemp.append("+0.02");
                }
                else{
                    roundOffValueTemp.delete(0, roundOffValueTemp.length());
                    roundOffValueTemp.append("+0.01");
                }
                secondValue = 5;
            }else if(secondValue==6||secondValue==7){
                if(secondValue==6){
                    roundOffValueTemp.delete(0, roundOffValueTemp.length());
                    roundOffValueTemp.append("-0.01");
                }
                else{
                    roundOffValueTemp.delete(0, roundOffValueTemp.length());
                    roundOffValueTemp.append("-0.02");
                }
                secondValue = 5;
            }else if(secondValue==8||secondValue==9){
                if(secondValue==8){
                    roundOffValueTemp.delete(0, roundOffValueTemp.length());
                    roundOffValueTemp.append("+0.02");
                }
                else{
                    roundOffValueTemp.delete(0, roundOffValueTemp.length());
                    roundOffValueTemp.append("+0.01");
                }
                if(firstValue == 9){
                    beforeDotValue++;
                    firstValue=0;
                }
                else
                    firstValue++;
                secondValue = 0;
            }else if(secondValue==5){
                secondValue = 5;
            }
        }catch(StringIndexOutOfBoundsException ex){
            secondValue = 0;
        }
        
        newValue.append(beforeDotValue);
        newValue.append(".");
        newValue.append(firstValue);
        newValue.append(secondValue);

        roundOffValue = roundOffValueTemp.toString();
        return Double.parseDouble(newValue.toString());
    }
    
    public double getAfterRoundOffValue(){
        return afterRoundOffValue;
    }
    
    public String getRoundOffValue(){
        return roundOffValue;
    }
}

