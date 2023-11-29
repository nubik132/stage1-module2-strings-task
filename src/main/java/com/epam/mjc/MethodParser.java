package com.epam.mjc;

import java.util.ArrayList;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] tokens =  signatureString.split("\\(");
        String signature = tokens[0];
        String args = tokens[1].substring(0, tokens[1].length() - 1);


        String[] signatureValues = signature.split(" ");

        String name = signatureValues[signatureValues.length - 1];
        String type = signatureValues[signatureValues.length - 2];

        String modifier = null;
        if (signatureValues.length > 2){
            modifier = signatureValues[0];
        }

        String[] argsTokens = args.split(", ");

        if (argsTokens[0] == "") argsTokens = new String[0];

        ArrayList<MethodSignature.Argument> argsList = new ArrayList<>();

        for (String token :
                argsTokens) {
            String[] argTokens = token.split(" ");
            argsList.add(new MethodSignature.Argument(argTokens[0], argTokens[1]));
        }

        MethodSignature methodSignature = new MethodSignature(name, argsList);
        methodSignature.setReturnType(type);
        if (modifier != null) methodSignature.setAccessModifier(modifier);

        return methodSignature;
    }
}
