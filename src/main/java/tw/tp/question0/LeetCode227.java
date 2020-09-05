package tw.tp.question0;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeetCode227{
    public static void main(String[] args) {
        int result1,result2;
        String testString = "0-7* 2 +15";
        result1 = new Solution1().calculate(testString);
        result2 = new Solution2().calculate(testString);
        System.out.println(result1);
        System.out.println(result2);

    }
}

class Solution1 {
    static class Symbol{
        String symbolType;
        int index;
        Symbol(String symbolType,int index){
            this.symbolType= symbolType;
            this.index= index;
        }
        @Override
        public String toString(){
            return "{"+this.symbolType+","+this.index+"}";
        }
    }

    public int calculate(String s) {
        s = s.replace(" ", "");
        ArrayList<Integer> numberList = new ArrayList<Integer>();
        ArrayList<Symbol> symbolList = new ArrayList<Symbol>();

        setNumberList(numberList, s);
        if(numberList.size() == 1){
            return numberList.get(0);
        }
        setSymbolList(symbolList, s);

        return startCalculate(symbolList, numberList);
    }

    void setNumberList(ArrayList<Integer> numberList, String s){
        String[] tempS = s.split("[+-/*//]");
        for(String t : tempS){
            int tempInt = Integer.parseInt(t);
            numberList.add(tempInt);
        }
    }

    void setSymbolList(ArrayList<Symbol> symbolList, String s){
        Pattern pattern = Pattern.compile("[+-/*//]");
        Matcher matcher = pattern.matcher(s);
        int count = 0;
        while ( matcher.find() ){
            char matchChar = s.charAt(matcher.start());
            String matchString = String.valueOf(matchChar);
            Symbol tempSymbol = new Symbol(matchString, count++);
            symbolList.add( tempSymbol );
        }

        class SymbolComparator implements Comparator<Symbol> {
            @Override
            public int compare(Symbol o1, Symbol o2) {
                Boolean o1TypeHighLevel =  o1.symbolType.equals("*") || o1.symbolType.equals("/");
                Boolean o2TypeHighLevel =  o2.symbolType.equals("*") || o2.symbolType.equals("/");
                if( o2TypeHighLevel ){
                    return 1;
                }else if( o1TypeHighLevel ){
                    return -1;
                }else{
                    return 0;
                }
            }
        }
        Comparator cmp = new SymbolComparator();
        Collections.sort(symbolList, cmp);
    }

    int startCalculate(ArrayList<Symbol> symbolList, ArrayList<Integer> numberList){
        int result = 0;

        Iterator<Symbol> symbolIterator =  symbolList.iterator();

        int count = 0;
        while( symbolIterator.hasNext() ){
            Symbol symbol = symbolIterator.next();
            int newIndex = symbol.index - count <= 0? 0 : symbol.index - count;
            int first = numberList.get(newIndex);
            int second = numberList.get(newIndex+1);
            numberList.remove(newIndex);
            switch (symbol.symbolType){
                case "+":{
                    result = add(first, second);
                    break;
                }
                case "-":{
                    result = sub(first, second);
                    break;
                }
                case "*":{
                    result = mul(first, second);
                    break;
                }
                case "/":{
                    result = div(first, second);
                    break;
                }
            }
            count++;
            numberList.set(newIndex, result);
        }
        return result;
    }

    int add(int a, int b){
        return a + b;
    }
    int sub(int a, int b){
        return a - b;
    }
    int mul(int a, int b){
        return a * b;
    }
    int div(int a, int b){
        return a / b;
    }
}

class Solution2 {
    Pattern allSymbolPattern= Pattern.compile("[+-/*/]");
    Pattern highLevelPattern= Pattern.compile("[/*/]");
    Pattern lowLevelPattern = Pattern.compile("[+-]");
    public int calculate(String s){
        String newS;
        s = s.replace(" ", "");
        Matcher allSymbolMatcher= allSymbolPattern.matcher(s);
        Matcher highLevelMatcher= highLevelPattern.matcher(s);
        Matcher lowLevelMatcher = lowLevelPattern.matcher(s);
        if( ! allSymbolMatcher.find() ){
            return Integer.parseInt(s);
        }
        int tempSymbolIndex;
        if(highLevelMatcher.find()){
            tempSymbolIndex = highLevelMatcher.start();
        }else{
            lowLevelMatcher.find();
            tempSymbolIndex = lowLevelMatcher.start();
            if(tempSymbolIndex == 0 && ! lowLevelMatcher.find()){
                return Integer.parseInt(s);
            }else{
                tempSymbolIndex = lowLevelMatcher.start();
            }
        }
        int preIndex = 0;
        int nextIndex = 0;
        Boolean haveFind = false;
        do{
            int tempIndex = allSymbolMatcher.start();
            if(tempSymbolIndex == tempIndex){
                haveFind = true;
            }else if(!haveFind){
                preIndex = tempIndex;
            }else{
                nextIndex = tempIndex;
                break;
            }
        }while(allSymbolMatcher.find());

        String firstNum = s.substring(0, tempSymbolIndex);
        if(preIndex != 0){
            firstNum = s.substring(preIndex+1, tempSymbolIndex);
        }
        String secondNum= s.substring(tempSymbolIndex+1);
        if(nextIndex != 0){
            secondNum= s.substring(tempSymbolIndex+1, nextIndex);
        }
        String symbol= s.substring(tempSymbolIndex, tempSymbolIndex+1);
        int tempResult = 0;
        switch (symbol){
            case "+" -> tempResult= add(Integer.parseInt(firstNum),Integer.parseInt(secondNum));
            case "-" -> tempResult= sub(Integer.parseInt(firstNum),Integer.parseInt(secondNum));
            case "*" -> tempResult= mul(Integer.parseInt(firstNum),Integer.parseInt(secondNum));
            case "/" -> tempResult= div(Integer.parseInt(firstNum),Integer.parseInt(secondNum));
        }
        if(preIndex== 0 && nextIndex == 0){
            newS = String.valueOf(tempResult);
        }else if(preIndex== 0){
            newS = tempResult + s.substring(nextIndex);
        }else if(nextIndex == 0 ){
            newS = s.substring(0, preIndex+1) + tempResult;
        }else{
            newS = s.substring(0, preIndex+1) + tempResult + s.substring(nextIndex);
        }
        return calculate(newS);
    }


    int add(int a, int b){
        return a + b;
    }
    int sub(int a, int b){
        return a - b;
    }
    int mul(int a, int b){
        return a * b;
    }
    int div(int a, int b){
        return a / b;
    }
}
