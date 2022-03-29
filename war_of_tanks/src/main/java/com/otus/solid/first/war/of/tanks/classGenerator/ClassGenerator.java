package com.otus.solid.first.war.of.tanks.classGenerator;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ClassGenerator {

    private static final String PATH_FOR_CLASSES_TO_GENERATE = "com.otus.solid.first.war.of.tanks.classGenerator.toGenerate";

    @SneakyThrows
    public static void main(String[] args) {
        List<? extends Class<?>> classByPath = getClassByPath(PATH_FOR_CLASSES_TO_GENERATE);
        String fieldName = "uObject";

        for (Class aClass : classByPath) {

            String simpleName = aClass.getSimpleName();


            Method[] methods = (aClass).getMethods();

            Iterable<MethodSpec> methodSpecIterable = generateMethodsSpec(simpleName, methods, fieldName);


            MethodSpec constructor = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(UObject.class, fieldName)
                    .addStatement("this.$N = $N", fieldName, fieldName)
                    .build();


            TypeSpec typeSpec = TypeSpec
                    .classBuilder(simpleName + "Adapter")
                    .addModifiers(Modifier.PUBLIC)
                    .addSuperinterface(classByPath.get(0))
                    .addField(UObject.class, fieldName, Modifier.PRIVATE, Modifier.FINAL)
                    .addMethod(constructor)
                    .addMethods(methodSpecIterable)
                    .build();

            JavaFile javaFile = JavaFile
                    .builder("com.otus.solid.first.war.of.tanks.classGenerator.generated", typeSpec)
                    .addFileComment("AUTOMATICALLY GENERATED at " + new Date())
                    .indent("    ")
                    .build();

//            javaFile.writeTo(System.out);

            Path path = Paths.get("war_of_tanks/src/main/java");
            javaFile.writeTo(path);
        }


    }

    private static Iterable<MethodSpec> generateMethodsSpec(String simpleName, Method[] methods, String fieldName) {
        return Arrays.stream(methods).map(method -> {

            if (method.getName().contains("get")) {
//                return generateGetter(simpleName, method, fieldName);
                return test(simpleName, method, fieldName);
            } else if (method.getName().contains("set")) {
//                return generateSetter(simpleName, method, fieldName);
                return test(simpleName, method, fieldName);
            } else {
                return test(simpleName, method, fieldName);
//                throw new IllegalArgumentException("Получен метод с неизвестным");
            }
        }).collect(Collectors.toList());
    }

    private static MethodSpec generateSetter(String simpleName, Method method, String fieldName) {
        return MethodSpec.methodBuilder(method.getName())
                .addModifiers(Modifier.PUBLIC)
                .returns(method.getReturnType())
                .addParameters(() -> Arrays.stream(method.getParameters())
                        .map(parameter -> ParameterSpec.builder(parameter.getType(), "newValue")
                                .build()).iterator())
                .addStatement("return $T.resolve($T.of(\"dependencyName\", \"$N::$N\", \"$N\", $N, \"newValue\", newValue))", IoC.class, Map.class, simpleName, method.getName(), fieldName, fieldName)
                .build();
    }

    private static MethodSpec test(String simpleName, Method method, String fieldName) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(method.getName());
        builder.addModifiers(Modifier.PUBLIC);

        if (ObjectUtils.notEqual("void", method.getReturnType().getSimpleName())) {
            builder.returns(method.getReturnType());
        }

        if (method.getParameters().length != 0) {
            builder.addParameters(() -> Arrays.stream(method.getParameters())
                    .map(parameter -> ParameterSpec.builder(parameter.getType(), parameter.getName())
                            .build()).iterator());
        }

        StringBuilder stringBuilder = new StringBuilder()
//                .append("return IoC.resolve(Map.of(\"dependencyName\", \"")
                .append(simpleName)
                .append("::")
                .append(method.getName())
                .append("\", ")
                .append("\"")
                .append(fieldName)
                .append("\", ")
                .append(fieldName);

        Arrays.stream(method.getParameters()).forEach(parameter -> {
            stringBuilder
                    .append(", \"")
                    .append(parameter.getName())
                    .append("\", ")
                    .append(parameter.getName());
        });


        stringBuilder.append("))");

        if (ObjectUtils.notEqual("void", method.getReturnType().getSimpleName())) {
            builder.addStatement("return $T.resolve($T.of(\"dependencyName\", \"$N", IoC.class, Map.class, stringBuilder.toString());
        } else {
            builder.addStatement("$T action = $T.resolve($T.of(\"dependencyName\", \"$N", Action.class, IoC.class, Map.class, stringBuilder.toString());
            builder.addStatement("action.execute()");

        }

        return builder
                .build();
    }

    private static MethodSpec generateGetter(String simpleName, Method method, String fieldName) {
        return MethodSpec.methodBuilder(method.getName())
                .addModifiers(Modifier.PUBLIC)
                .returns(method.getReturnType())
                .addParameters(() -> Arrays.stream(method.getParameters())
                        .map(parameter -> ParameterSpec.builder(parameter.getType(), "newValue")
                                .build()).iterator())
                .addStatement("return IoC.resolve(Map.of(\"dependencyName\", \"$N::$N\", \"$N\", $N))", simpleName, method.getName(), fieldName, fieldName)
                .build();
    }

    public static List<? extends Class<?>> getClassByPath(String pathForClassesToGenerate) {
        return new Reflections(pathForClassesToGenerate, new SubTypesScanner(false))
                .getAllTypes()
                .stream()
                .map(name -> {
                    try {
                        return Class.forName(name);
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(aClass -> !"java.lang.Object".equals(aClass.getCanonicalName()))
                .toList();
    }
}
