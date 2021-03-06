package io.github.marmer.annotationprocessing;

import com.google.common.truth.Truth;
import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.JavaSourcesSubjectFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.tools.JavaFileObject;
import java.time.LocalDate;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

class MatcherGenerationProcessorITest {


    @Test
    @DisplayName("Matcher should have been generated for Pojo from Source file")
    void testGenerate_MatcherShouldHaveBeenGeneratedForPojoFromSourceFile() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfigurations;\n" +
                "\n" +
                "@MatcherConfigurations(@MatcherConfiguration(\"some.other.pck.SimplePojo\"))\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SimplePojo", "package some.other.pck;\n" +
                "\n" +
                "public class SimplePojo{\n" +
                "    private String nonPropertyField;\n" +
                "    private static String staticNonPropertyField;\n" +
                "    \n" +
                "    public String getSomeStringProperty(){\n" +
                "        return \"someValue\";\n" +
                "    }\n" +
                "\n" +
                "    public boolean isSomePrimitiveBooleanProperty(){\n" +
                "        return true;\n" +
                "    }\n" +
                "\n" +
                "    public Boolean getSomeNonePrimitiveBooleanProperty(){\n" +
                "        return false;\n" +
                "    }\n" +
                "\n" +
                "    public String someNonPropertyMethod(){\n" +
                "        return \">o.O<\";\n" +
                "    }\n" +
                "\n" +
                "    public void getPropertyLikeVoidMethod(){\n" +
                "    }\n" +
                "\n" +
                "    public String getSomePropertyLikeMethodWithParameters(int param){\n" +
                "        return String.valueOf(param);\n" +
                "    }\n" +
                "    \n" +
                "    public boolean getSomePropertyLike(){\n" +
                "        return true;\n" +
                "    }\n" +
                "    \n" +
                "    public static String getStaticPropertyLikeReturnValue(){\n" +
                "        return \"nope\";\n" +
                "    }\n" +
                "    \n" +
                "    public static class InnerStaticPojo{\n" +
                "        public String getInnerStaticPojoProperty(){\n" +
                "            return \"an inner pojo property value\";\n" +
                "        }\n" +
                "        \n" +
                "        public static class InnerInnerStaticPojo{\n" +
                "        }\n" +
                "    }\n" +
                "}");

        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.OutputClass", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "import javax.annotation.Generated;\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojo.class)\n" +
                "public class SimplePojoMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojo> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojoMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo>(SimplePojo.class);\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withSomeStringProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"someStringProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withSomeStringProperty(final String value) {\n" +
                "        beanPropertyMatcher.with(\"someStringProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withSomePrimitiveBooleanProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"somePrimitiveBooleanProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withSomePrimitiveBooleanProperty(final boolean value) {\n" +
                "        beanPropertyMatcher.with(\"somePrimitiveBooleanProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withSomeNonePrimitiveBooleanProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"someNonePrimitiveBooleanProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withSomeNonePrimitiveBooleanProperty(final Boolean value) {\n" +
                "        beanPropertyMatcher.with(\"someNonePrimitiveBooleanProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withClass(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"class\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withClass(final Class value) {\n" +
                "        beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static SimplePojoMatcher isSimplePojo() {\n" +
                "        return new SimplePojoMatcher();\n" +
                "    }\n" +
                "\n" +
                "    @Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "    @BasedOn(SimplePojo.InnerStaticPojo.class)\n" +
                "    public static class InnerStaticPojoMatcher extends TypeSafeMatcher<Object> {\n" +
                "        private final BeanPropertyMatcher<SimplePojo.InnerStaticPojo> beanPropertyMatcher;\n" +
                "\n" +
                "        public InnerStaticPojoMatcher() {\n" +
                "            beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo.InnerStaticPojo>(SimplePojo.InnerStaticPojo.class);\n" +
                "        }\n" +
                "\n" +
                "        public InnerStaticPojoMatcher withInnerStaticPojoProperty(final Matcher<?> matcher) {\n" +
                "            beanPropertyMatcher.with(\"innerStaticPojoProperty\", matcher);\n" +
                "            return this;\n" +
                "        }\n" +
                "\n" +
                "        public InnerStaticPojoMatcher withInnerStaticPojoProperty(final String value) {\n" +
                "            beanPropertyMatcher.with(\"innerStaticPojoProperty\", Matchers.equalTo(value));\n" +
                "            return this;\n" +
                "        }\n" +
                "\n" +
                "        public InnerStaticPojoMatcher withClass(final Matcher<?> matcher) {\n" +
                "            beanPropertyMatcher.with(\"class\", matcher);\n" +
                "            return this;\n" +
                "        }\n" +
                "\n" +
                "        public InnerStaticPojoMatcher withClass(final Class value) {\n" +
                "            beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "            return this;\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void describeTo(final Description description) {\n" +
                "            beanPropertyMatcher.describeTo(description);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        protected boolean matchesSafely(final Object item) {\n" +
                "            return beanPropertyMatcher.matches(item);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "            beanPropertyMatcher.describeMismatch(item, description);\n" +
                "        }\n" +
                "\n" +
                "        public static InnerStaticPojoMatcher isInnerStaticPojo() {\n" +
                "            return new InnerStaticPojoMatcher();\n" +
                "        }\n" +
                "        @Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "        @BasedOn(SimplePojo.InnerStaticPojo.InnerInnerStaticPojo.class)\n" +
                "        public static class InnerInnerStaticPojoMatcher extends TypeSafeMatcher<Object> {\n" +
                "            private final BeanPropertyMatcher<SimplePojo.InnerStaticPojo.InnerInnerStaticPojo> beanPropertyMatcher;\n" +
                "\n" +
                "            public InnerInnerStaticPojoMatcher() {\n" +
                "                beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo.InnerStaticPojo.InnerInnerStaticPojo>(SimplePojo.InnerStaticPojo.InnerInnerStaticPojo.class);\n" +
                "            }\n" +
                "\n" +
                "            public InnerInnerStaticPojoMatcher withClass(final Matcher<?> matcher) {\n" +
                "                beanPropertyMatcher.with(\"class\", matcher);\n" +
                "                return this;\n" +
                "            }\n" +
                "\n" +
                "            public InnerInnerStaticPojoMatcher withClass(final Class value) {\n" +
                "                beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "                return this;\n" +
                "            }\n" +
                "\n" +
                "            @Override\n" +
                "            public void describeTo(final Description description) {\n" +
                "                beanPropertyMatcher.describeTo(description);\n" +
                "            }\n" +
                "\n" +
                "            @Override\n" +
                "            protected boolean matchesSafely(final Object item) {\n" +
                "                return beanPropertyMatcher.matches(item);\n" +
                "            }\n" +
                "\n" +
                "            @Override\n" +
                "            protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "                beanPropertyMatcher.describeMismatch(item, description);\n" +
                "            }\n" +
                "\n" +
                "            public static InnerInnerStaticPojoMatcher isInnerInnerStaticPojo() {\n" +
                "                return new InnerInnerStaticPojoMatcher();\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}"
        );

        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }

    @Test
    @DisplayName("Matcher should be generated for Interfaces with property methods")
    void testGenerate_MatcherShouldBeGeneratedForInterfacesWithPropertyMethods() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfigurations;\n" +
                "\n" +
                "@MatcherConfigurations(@MatcherConfiguration(\"some.other.pck.SimplePojoInterface\"))\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SimplePojoInterface", "package some.other.pck;\n" +
                "\n" +
                "public interface SimplePojoInterface{\n" +
                "    String getSomeStringProperty();\n" +
                "}");

        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.OutputClass", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "import javax.annotation.Generated;\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojoInterface.class)\n" +
                "public class SimplePojoInterfaceMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojoInterface> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojoInterface>(SimplePojoInterface.class);\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher withSomeStringProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"someStringProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher withSomeStringProperty(final String value) {\n" +
                "        beanPropertyMatcher.with(\"someStringProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "    \n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "    \n" +
                "    public static SimplePojoInterfaceMatcher isSimplePojoInterface() {\n" +
                "        return new SimplePojoInterfaceMatcher();\n" +
                "    }\n" +
                "}");

        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }

    @Test
    @DisplayName("Matcher should be generated for arrays")
    void testGenerate_MatcherShouldBeGeneratedForArrays() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfigurations;\n" +
                "\n" +
                "@MatcherConfigurations(@MatcherConfiguration(\"some.other.pck.SimplePojoInterface\"))\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SimplePojoInterface", "package some.other.pck;\n" +
                "\n" +
                "public interface SimplePojoInterface{\n" +
                "     String[] getSomeStringArray();\n" +
                "     String[][] getSomeMultidimensionalStringArray();\n" +
                "    AnotherComplexType.SomeInnerType[] getSomeInnerTypeArray();\n" +
                "    byte[] getSomePrimitiveArray();\n" +
                "    byte[][] getSomeMultidimensionalPrimitiveArray();\n" +
                "}");
        final JavaFileObject javaFileObjectWithComplexInnerTypes = JavaFileObjects.forSourceLines("some.other.pck.AnotherComplexType", "package some.other.pck;\n" +
                "\n" +
                "public interface AnotherComplexType{\n" +
                "    interface SomeInnerType{\n" +
                "        String getSomeStringProperty();\n" +
                "    }\n" +
                "}");

        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.OutputClass", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "\n" +
                "import javax.annotation.Generated;\n" +
                "\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojoInterface.class)\n" +
                "public class SimplePojoInterfaceMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojoInterface> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojoInterface>(SimplePojoInterface.class);\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher withSomeStringArray(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"someStringArray\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher withSomeStringArray(final String[] value) {\n" +
                "        beanPropertyMatcher.with(\"someStringArray\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher withSomeMultidimensionalStringArray(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"someMultidimensionalStringArray\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher withSomeMultidimensionalStringArray(final String[][] value) {\n" +
                "        beanPropertyMatcher.with(\"someMultidimensionalStringArray\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher withSomeInnerTypeArray(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"someInnerTypeArray\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher withSomeInnerTypeArray(final AnotherComplexType.SomeInnerType[] value) {\n" +
                "        beanPropertyMatcher.with(\"someInnerTypeArray\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher withSomePrimitiveArray(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"somePrimitiveArray\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher withSomePrimitiveArray(final byte[] value) {\n" +
                "        beanPropertyMatcher.with(\"somePrimitiveArray\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher withSomeMultidimensionalPrimitiveArray(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"someMultidimensionalPrimitiveArray\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoInterfaceMatcher withSomeMultidimensionalPrimitiveArray(final byte[][] value) {\n" +
                "        beanPropertyMatcher.with(\"someMultidimensionalPrimitiveArray\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static SimplePojoInterfaceMatcher isSimplePojoInterface() {\n" +
                "        return new SimplePojoInterfaceMatcher();\n" +
                "    }\n" +
                "}");

        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject, javaFileObjectWithComplexInnerTypes))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }

    @Test
    @DisplayName("Matcher should be generated for inner non static classes")
    void testGenerate_MatcherShouldBeGeneratedForInnerNonStaticClasses() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfigurations;\n" +
                "\n" +
                "@MatcherConfigurations(@MatcherConfiguration(\"some.other.pck.SomeClass\"))\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SomeClass", "package some.other.pck;\n" +
                "\n" +
                "public class SomeClass{\n" +
                "    public class SomeNonStaticInnerClass{\n" +
                "        \n" +
                "    }\n" +
                "}");

        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.OutputClass", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "import javax.annotation.Generated;\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SomeClass.class)\n" +
                "public class SomeClassMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SomeClass> beanPropertyMatcher;\n" +
                "\n" +
                "    public SomeClassMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SomeClass>(SomeClass.class);\n" +
                "    }\n" +
                "\n" +
                "    public SomeClassMatcher withClass(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"class\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SomeClassMatcher withClass(final Class value) {\n" +
                "        beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "    \n" +
                "    public static SomeClassMatcher isSomeClass() {\n" +
                "        return new SomeClassMatcher();\n" +
                "    }\n" +
                "\n" +
                "    @Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "    @BasedOn(SomeClass.SomeNonStaticInnerClass.class)\n" +
                "    public static class SomeNonStaticInnerClassMatcher extends TypeSafeMatcher<Object> {\n" +
                "        private final BeanPropertyMatcher<SomeClass.SomeNonStaticInnerClass> beanPropertyMatcher;\n" +
                "\n" +
                "        public SomeNonStaticInnerClassMatcher() {\n" +
                "            beanPropertyMatcher = new BeanPropertyMatcher<SomeClass.SomeNonStaticInnerClass>(SomeClass.SomeNonStaticInnerClass.class);\n" +
                "        }\n" +
                "\n" +
                "        public SomeNonStaticInnerClassMatcher withClass(final Matcher<?> matcher) {\n" +
                "            beanPropertyMatcher.with(\"class\", matcher);\n" +
                "            return this;\n" +
                "        }\n" +
                "\n" +
                "        public SomeNonStaticInnerClassMatcher withClass(final Class value) {\n" +
                "            beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "            return this;\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void describeTo(final Description description) {\n" +
                "            beanPropertyMatcher.describeTo(description);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        protected boolean matchesSafely(final Object item) {\n" +
                "            return beanPropertyMatcher.matches(item);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "            beanPropertyMatcher.describeMismatch(item, description);\n" +
                "        }\n" +
                "\n" +
                "        public static SomeNonStaticInnerClassMatcher isSomeNonStaticInnerClass() {\n" +
                "            return new SomeNonStaticInnerClassMatcher();\n" +
                "        }\n" +
                "    }\n" +
                "}");

        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }

    @Test
    @DisplayName("Generated Matchers should work for inner interfaces")
    void testGenerate_GeneratedMatchersShouldWorkForInnerInterfaces() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfigurations;\n" +
                "\n" +
                "@MatcherConfigurations(@MatcherConfiguration(\"some.other.pck.SomePojo\"))\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SomePojo", "package some.other.pck;\n" +
                "\n" +
                "public class SomePojo{\n" +
                "    public interface InnerInterface {\n" +
                "    }\n" +
                "}");

        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.SomePojoMatcher", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "\n" +
                "import javax.annotation.Generated;\n" +
                "\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SomePojo.class)\n" +
                "public class SomePojoMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SomePojo> beanPropertyMatcher;\n" +
                "\n" +
                "    public SomePojoMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SomePojo>(SomePojo.class);\n" +
                "    }\n" +
                "\n" +
                "    public SomePojoMatcher withClass(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"class\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SomePojoMatcher withClass(final Class value) {\n" +
                "        beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static SomePojoMatcher isSomePojo() {\n" +
                "        return new SomePojoMatcher();\n" +
                "    }\n" +
                "\n" +
                "    @Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "    @BasedOn(SomePojo.InnerInterface.class)\n" +
                "    public static class InnerInterfaceMatcher extends TypeSafeMatcher<Object> {\n" +
                "        private final BeanPropertyMatcher<SomePojo.InnerInterface> beanPropertyMatcher;\n" +
                "\n" +
                "        public InnerInterfaceMatcher() {\n" +
                "            beanPropertyMatcher = new BeanPropertyMatcher<SomePojo.InnerInterface>(SomePojo.InnerInterface.class);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void describeTo(final Description description) {\n" +
                "            beanPropertyMatcher.describeTo(description);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        protected boolean matchesSafely(final Object item) {\n" +
                "            return beanPropertyMatcher.matches(item);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "            beanPropertyMatcher.describeMismatch(item, description);\n" +
                "        }\n" +
                "\n" +
                "        public static InnerInterfaceMatcher isInnerInterface() {\n" +
                "            return new InnerInterfaceMatcher();\n" +
                "        }\n" +
                "    }\n" +
                "}");

        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }

    @Test
    @DisplayName("Matcher should be generated for Enums with property methods")
    void testGenerate_MatcherShouldBeGeneratedForEnumsWithPropertyMethods() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfigurations;\n" +
                "\n" +
                "@MatcherConfigurations(@MatcherConfiguration(\"some.other.pck.SimplePojoEnum\"))\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SimplePojoEnum", "package some.other.pck;\n" +
                "\n" +
                "public enum SimplePojoEnum{\n" +
                "    SOME_ENUM_CONSTANT;\n" +
                "    public String getSomeStringProperty(){\n" +
                "        return \"someValue\";\n" +
                "    }\n" +
                "}");

        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.OutputClass", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "import javax.annotation.Generated;\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojoEnum.class)\n" +
                "public class SimplePojoEnumMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojoEnum> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojoEnumMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojoEnum>(SimplePojoEnum.class);\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoEnumMatcher withSomeStringProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"someStringProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoEnumMatcher withSomeStringProperty(final String value) {\n" +
                "        beanPropertyMatcher.with(\"someStringProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoEnumMatcher withDeclaringClass(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"declaringClass\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoEnumMatcher withDeclaringClass(final Class value) {\n" +
                "        beanPropertyMatcher.with(\"declaringClass\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoEnumMatcher withClass(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"class\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoEnumMatcher withClass(final Class value) {\n" +
                "        beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "    \n" +
                "    public static SimplePojoEnumMatcher isSimplePojoEnum() {\n" +
                "        return new SimplePojoEnumMatcher();\n" +
                "    }\n" +
                "}");

        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }


    @Test
    @DisplayName("Matcher should contain inherited properties")
    void testGenerate_MatcherShouldContainInheritedProperties() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfigurations;\n" +
                "\n" +
                "@MatcherConfigurations(@MatcherConfiguration(\"some.other.pck.SimplePojo\"))\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject parentPojo = JavaFileObjects.forSourceLines("some.other.pck.ParentPojo", "package some.other.pck;\n" +
                "\n" +
                "public class ParentPojo<T>{\n" +
                "    public CharSequence getPropertyOfBothClasses(){\n" +
                "        return \"someFancyValue\";\n" +
                "    }\n" +
                "    \n" +
                "    public T getParentPojoProperty(){\n" +
                "        return null;\n" +
                "    }\n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SimplePojo", "package some.other.pck;\n" +
                "\n" +
                "public class SimplePojo extends ParentPojo<String>{\n" +
                "    public String getPropertyOfBothClasses(){\n" +
                "        return \"someFancyValue\";\n" +
                "    }\n" +
                "}");

        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.OutputClass", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "import javax.annotation.Generated;\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojo.class)\n" +
                "public class SimplePojoMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojo> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojoMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo>(SimplePojo.class);\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withPropertyOfBothClasses(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"propertyOfBothClasses\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withPropertyOfBothClasses(final String value) {\n" +
                "        beanPropertyMatcher.with(\"propertyOfBothClasses\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withParentPojoProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"parentPojoProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withParentPojoProperty(final Object value) {\n" +
                "        beanPropertyMatcher.with(\"parentPojoProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withClass(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"class\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withClass(final Class value) {\n" +
                "        beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "    \n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "    \n" +
                "    public static SimplePojoMatcher isSimplePojo() {\n" +
                "        return new SimplePojoMatcher();\n" +
                "    }\n" +
                "}");

        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject, parentPojo))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }

    @Test
    @DisplayName("Matcher and matcher methods should not be generated for non public types and properties")
    void testGenerate_MatcherAndMatcherMethodsShouldNotBeGeneratedForNonPublicTypesAndProperties() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfigurations;\n" +
                "\n" +
                "@MatcherConfigurations(@MatcherConfiguration(\"some.other.pck.SimplePojo\"))\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SimplePojo", "package some.other.pck;\n" +
                "\n" +
                "public class SimplePojo{\n" +
                "    private String getPrivatePropertyProperty(){\n" +
                "        return \"piv\";\n" +
                "    }\n" +
                "     String getPackagePrivatePropertyProperty(){\n" +
                "        return \"def\";\n" +
                "    }\n" +
                "    \n" +
                "    protected String getProtectedPropertyProperty(){\n" +
                "        return \"pro\";\n" +
                "    }\n" +
                "    private class PrivateClass{\n" +
                "        \n" +
                "    }\n" +
                "    class PackagePrivateClass{\n" +
                "        \n" +
                "    }\n" +
                "}");

        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.OutputClass", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "import javax.annotation.Generated;\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojo.class)\n" +
                "public class SimplePojoMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojo> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojoMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo>(SimplePojo.class);\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withClass(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"class\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withClass(final Class value) {\n" +
                "        beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static SimplePojoMatcher isSimplePojo() {\n" +
                "        return new SimplePojoMatcher();\n" +
                "    }\n" +
                "}");

        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput)
                .withNoteContaining("Hamcrest-Matcher-Generator: Processing skipped for non public type: some.other.pck.SimplePojo.PrivateClass")
                .in(javaFileObject)
                .onLine(14)
                .atColumn(13)
                .and()
                .withNoteContaining("Hamcrest-Matcher-Generator: Processing skipped for non public type: some.other.pck.SimplePojo.PackagePrivateClass")
                .in(javaFileObject)
                .onLine(17)
                .atColumn(5);
    }

    @Test
    @DisplayName("Matcher should be generated for types of outer dependencies as well")
    void testGenerate_MatcherShouldBeGeneratedForTypesOfOuterDependenciesAsWell() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfigurations;\n" +
                "\n" +
                "@MatcherConfigurations(@MatcherConfiguration(\"org.mockito.ArgumentMatchers\"))\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("org.mockito.ArgumentMatchersMatcher", "package org.mockito;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "import javax.annotation.Generated;\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(ArgumentMatchers.class)\n" +
                "public class ArgumentMatchersMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<ArgumentMatchers> beanPropertyMatcher;\n" +
                "\n" +
                "    public ArgumentMatchersMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<ArgumentMatchers>(ArgumentMatchers.class);\n" +
                "    }\n" +
                "\n" +
                "    public ArgumentMatchersMatcher withClass(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"class\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public ArgumentMatchersMatcher withClass(final Class value) {\n" +
                "        beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static ArgumentMatchersMatcher isArgumentMatchers() {\n" +
                "        return new ArgumentMatchersMatcher();\n" +
                "    }\n" +
                "}");

        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(singletonList(configuration))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }


    @Test
    @DisplayName("Matchers should be generated for all classes directly located in a configured package")
    void testGenerate_MatchersShouldBeGeneratedForAllClassesDirectlyLocatedInAConfiguredPackage() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfigurations;\n" +
                "\n" +
                "@MatcherConfigurations(@MatcherConfiguration(\"some.other.pck\"))\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject1 = JavaFileObjects.forSourceLines("some.other.pck.SimplePojo1", "package some.other.pck;\n" +
                "\n" +
                "public class SimplePojo1{\n" +
                "}");

        final JavaFileObject javaFileObject2 = JavaFileObjects.forSourceLines("some.other.pck.SimplePojo2", "package some.other.pck;\n" +
                "\n" +
                "public class SimplePojo2{\n" +
                "}");

        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput1 = JavaFileObjects.forSourceString("sample.other.pck.SimplePojo11Matcher", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "\n" +
                "import javax.annotation.Generated;\n" +
                "\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojo1.class)\n" +
                "public class SimplePojo1Matcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojo1> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojo1Matcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo1>(SimplePojo1.class);\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojo1Matcher withClass(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"class\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojo1Matcher withClass(final Class value) {\n" +
                "        beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static SimplePojo1Matcher isSimplePojo1() {\n" +
                "        return new SimplePojo1Matcher();\n" +
                "    }\n" +
                "}");
        final JavaFileObject expectedOutput2 = JavaFileObjects.forSourceString("sample.other.pck.SimplePojo22Matcher", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "import javax.annotation.Generated;\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojo2.class)\n" +
                "public class SimplePojo2Matcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojo2> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojo2Matcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo2>(SimplePojo2.class);\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojo2Matcher withClass(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"class\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojo2Matcher withClass(final Class value) {\n" +
                "        beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static SimplePojo2Matcher isSimplePojo2() {\n" +
                "        return new SimplePojo2Matcher();\n" +
                "    }\n" +
                "}");

        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject1, javaFileObject2))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput1, expectedOutput2);
    }

    @Test
    @DisplayName("Warns on not existing packages or types")
    void testGenerate_WarnsOnNotExistingPackagesOrTypes() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfigurations;\n" +
                "\n" +
                "@MatcherConfigurations(@MatcherConfiguration({\"not.existing.pck\"}))\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SimplePojo", "package some.other.pck;\n" +
                "\n" +
                "public class SimplePojo{\n" +
                "}");

        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .withWarningContaining("Hamcrest-Matcher-Generator: Package or type does not exist: not.existing.pck")
                .in(configuration)
                .onLine(7)
                .atColumn(14);
    }

    @Test
    @DisplayName("Generation should work for MatcherConfiguration (singular) as well")
    void testGenerate_GenerationSholdWorkForMatcherConfigurationAsWell() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "\n" +
                "@MatcherConfiguration({\"some.other.pck.SimplePojo\"})\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SimplePojo", "package some.other.pck;\n" +
                "\n" +
                "public class SimplePojo{\n" +
                "}");
        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.SimplePojoMatcher", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "\n" +
                "import javax.annotation.Generated;\n" +
                "\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojo.class)\n" +
                "public class SimplePojoMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojo> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojoMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo>(SimplePojo.class);\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withClass(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"class\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withClass(final Class value) {\n" +
                "        beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static SimplePojoMatcher isSimplePojo() {\n" +
                "        return new SimplePojoMatcher();\n" +
                "    }\n" +
                "}");
        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }

    @Test
    @DisplayName("Generation should work for properties of inner enums")
    void testGenerate_GenerationShouldWorkForPropertiesOfInnerEnums() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "\n" +
                "@MatcherConfiguration({\"some.other.pck.SimplePojo\"})\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SimplePojo", "package some.other.pck;\n" +
                "\n" +
                "public interface SimplePojo {\n" +
                "    interface InnerType {\n" +
                "        enum InnerEnum {\n" +
                "            ONE, TWO\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    InnerType.InnerEnum getSomeProperty();\n" +
                "}");
        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.SimplePojoMatcher", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "\n" +
                "import javax.annotation.Generated;\n" +
                "\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojo.class)\n" +
                "public class SimplePojoMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojo> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojoMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo>(SimplePojo.class);\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withSomeProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"someProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withSomeProperty(final SimplePojo.InnerType.InnerEnum value) {\n" +
                "        beanPropertyMatcher.with(\"someProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static SimplePojoMatcher isSimplePojo() {\n" +
                "        return new SimplePojoMatcher();\n" +
                "    }\n" +
                "\n" +
                "    @Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "    @BasedOn(SimplePojo.InnerType.class)\n" +
                "    public static class InnerTypeMatcher extends TypeSafeMatcher<Object> {\n" +
                "        private final BeanPropertyMatcher<SimplePojo.InnerType> beanPropertyMatcher;\n" +
                "\n" +
                "        public InnerTypeMatcher() {\n" +
                "            beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo.InnerType>(SimplePojo.InnerType.class);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void describeTo(final Description description) {\n" +
                "            beanPropertyMatcher.describeTo(description);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        protected boolean matchesSafely(final Object item) {\n" +
                "            return beanPropertyMatcher.matches(item);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        protected void describeMismatchSafely(final Object item,\n" +
                "                                              final Description description) {\n" +
                "            beanPropertyMatcher.describeMismatch(item, description);\n" +
                "        }\n" +
                "\n" +
                "        public static InnerTypeMatcher isInnerType() {\n" +
                "            return new InnerTypeMatcher();\n" +
                "        }\n" +
                "\n" +
                "        @Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "        @BasedOn(SimplePojo.InnerType.InnerEnum.class)\n" +
                "        public static class InnerEnumMatcher extends TypeSafeMatcher<Object> {\n" +
                "            private final BeanPropertyMatcher<SimplePojo.InnerType.InnerEnum> beanPropertyMatcher;\n" +
                "\n" +
                "            public InnerEnumMatcher() {\n" +
                "                beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo.InnerType.InnerEnum>(SimplePojo.InnerType.InnerEnum.class);\n" +
                "            }\n" +
                "\n" +
                "            public InnerEnumMatcher withDeclaringClass(final Matcher<?> matcher) {\n" +
                "                beanPropertyMatcher.with(\"declaringClass\", matcher);\n" +
                "                return this;\n" +
                "            }\n" +
                "\n" +
                "            public InnerEnumMatcher withDeclaringClass(final Class value) {\n" +
                "                beanPropertyMatcher.with(\"declaringClass\", Matchers.equalTo(value));\n" +
                "                return this;\n" +
                "            }\n" +
                "\n" +
                "            public InnerEnumMatcher withClass(final Matcher<?> matcher) {\n" +
                "                beanPropertyMatcher.with(\"class\", matcher);\n" +
                "                return this;\n" +
                "            }\n" +
                "\n" +
                "            public InnerEnumMatcher withClass(final Class value) {\n" +
                "                beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "                return this;\n" +
                "            }\n" +
                "\n" +
                "            @Override\n" +
                "            public void describeTo(final Description description) {\n" +
                "                beanPropertyMatcher.describeTo(description);\n" +
                "            }\n" +
                "\n" +
                "            @Override\n" +
                "            protected boolean matchesSafely(final Object item) {\n" +
                "                return beanPropertyMatcher.matches(item);\n" +
                "            }\n" +
                "\n" +
                "            @Override\n" +
                "            protected void describeMismatchSafely(final Object item,\n" +
                "                                                  final Description description) {\n" +
                "                beanPropertyMatcher.describeMismatch(item, description);\n" +
                "            }\n" +
                "\n" +
                "            public static InnerEnumMatcher isInnerEnum() {\n" +
                "                return new InnerEnumMatcher();\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }

    @Test
    @DisplayName("Configured inner types should also generate all outer matchers")
    void testGenerate_ConfiguredInnerTypesShouldAlsoGenerateAllOuterMatchers() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "\n" +
                "@MatcherConfiguration({\"some.other.pck.SimplePojo.InnerType.InnerInnerType\"})\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SimplePojo", "package some.other.pck;\n" +
                "\n" +
                "public interface SimplePojo {\n" +
                "    interface InnerType {\n" +
                "        interface InnerInnerType {\n" +
                "        }\n" +
                "    }\n" +
                "}");
        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.SimplePojoMatcher", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "\n" +
                "import javax.annotation.Generated;\n" +
                "\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojo.class)\n" +
                "public class SimplePojoMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojo> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojoMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo>(SimplePojo.class);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static SimplePojoMatcher isSimplePojo() {\n" +
                "        return new SimplePojoMatcher();\n" +
                "    }\n" +
                "\n" +
                "    @Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "    @BasedOn(SimplePojo.InnerType.class)\n" +
                "    public static class InnerTypeMatcher extends TypeSafeMatcher<Object> {\n" +
                "        private final BeanPropertyMatcher<SimplePojo.InnerType> beanPropertyMatcher;\n" +
                "\n" +
                "        public InnerTypeMatcher() {\n" +
                "            beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo.InnerType>(SimplePojo.InnerType.class);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void describeTo(final Description description) {\n" +
                "            beanPropertyMatcher.describeTo(description);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        protected boolean matchesSafely(final Object item) {\n" +
                "            return beanPropertyMatcher.matches(item);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        protected void describeMismatchSafely(final Object item,\n" +
                "                                              final Description description) {\n" +
                "            beanPropertyMatcher.describeMismatch(item, description);\n" +
                "        }\n" +
                "\n" +
                "        public static InnerTypeMatcher isInnerType() {\n" +
                "            return new InnerTypeMatcher();\n" +
                "        }\n" +
                "\n" +
                "        @Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "        @BasedOn(SimplePojo.InnerType.InnerInnerType.class)\n" +
                "        public static class InnerInnerTypeMatcher extends TypeSafeMatcher<Object> {\n" +
                "            private final BeanPropertyMatcher<SimplePojo.InnerType.InnerInnerType> beanPropertyMatcher;\n" +
                "\n" +
                "            public InnerInnerTypeMatcher() {\n" +
                "                beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo.InnerType.InnerInnerType>(SimplePojo.InnerType.InnerInnerType.class);\n" +
                "            }\n" +
                "\n" +
                "            @Override\n" +
                "            public void describeTo(final Description description) {\n" +
                "                beanPropertyMatcher.describeTo(description);\n" +
                "            }\n" +
                "\n" +
                "            @Override\n" +
                "            protected boolean matchesSafely(final Object item) {\n" +
                "                return beanPropertyMatcher.matches(item);\n" +
                "            }\n" +
                "\n" +
                "            @Override\n" +
                "            protected void describeMismatchSafely(final Object item,\n" +
                "                                                  final Description description) {\n" +
                "                beanPropertyMatcher.describeMismatch(item, description);\n" +
                "            }\n" +
                "\n" +
                "            public static InnerInnerTypeMatcher isInnerInnerType() {\n" +
                "                return new InnerInnerTypeMatcher();\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }

    @Test
    @DisplayName("Only a single matcher method should be generated for properties of type Matcher")
    void testGenerate_OnlyASingleMatcherMethodShouldBeGeneratedForPropertiesOfTypeMatcher() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "\n" +
                "@MatcherConfiguration({\"some.other.pck.SimplePojo\"})\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SimplePojo", "package some.other.pck;\n" +
                "\n" +
                "import org.hamcrest.Matcher;\n" +
                "\n" +
                "public interface SimplePojo {\n" +
                "    Matcher<String> getProperty();\n" +
                "}");
        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.SimplePojoMatcher", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "\n" +
                "import javax.annotation.Generated;\n" +
                "\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojo.class)\n" +
                "public class SimplePojoMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojo> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojoMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo>(SimplePojo.class);\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"property\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static SimplePojoMatcher isSimplePojo() {\n" +
                "        return new SimplePojoMatcher();\n" +
                "    }\n" +
                "}");
        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }

    @Test
    @DisplayName("Generics and wildcard properties should be handled like Object")
    void testGenerate_GenericsAndWildcardPropertiesShouldBeHandledLikeObject() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "\n" +
                "@MatcherConfiguration({\"some.other.pck.SimplePojo\"})\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SimplePojo", "package some.other.pck;\n" +
                "\n" +
                "import org.hamcrest.Matcher;\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "public interface SimplePojo <T>{\n" +
                "    T getProperty();\n" +
                "    List<? extends String> getWildcardProperty();\n" +
                "}");

        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.SimplePojoMatcher", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "\n" +
                "import java.util.List;\n" +
                "import javax.annotation.Generated;\n" +
                "\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojo.class)\n" +
                "public class SimplePojoMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojo> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojoMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo>(SimplePojo.class);\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"property\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withProperty(final Object value) {\n" +
                "        beanPropertyMatcher.with(\"property\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withWildcardProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"wildcardProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withWildcardProperty(final List value) {\n" +
                "        beanPropertyMatcher.with(\"wildcardProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static SimplePojoMatcher isSimplePojo() {\n" +
                "        return new SimplePojoMatcher();\n" +
                "    }\n" +
                "}");
        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }

    @Test
    @DisplayName("Generation should work properly for primitives")
    void testGenerate_GenerationShouldWorkProperlyForPrimitives() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "\n" +
                "@MatcherConfiguration({\"some.other.pck.SimplePojo\"})\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SimplePojo", "package some.other.pck;\n" +
                "\n" +
                "public class SimplePojo{\n" +
                "    public int getIntProperty(){\n" +
                "        return 1;\n" +
                "    }\n" +
                "    public short getShortProperty(){\n" +
                "        return 2;\n" +
                "    }\n" +
                "    public long getLongProperty(){\n" +
                "        return 3;\n" +
                "    }\n" +
                "    public double getDoubleProperty(){\n" +
                "        return 4;\n" +
                "    }\n" +
                "    public float getFloatProperty(){\n" +
                "        return 5;\n" +
                "    }\n" +
                "    public char getCharProperty(){\n" +
                "        return 6;\n" +
                "    }\n" +
                "    public byte getByteProperty(){\n" +
                "        return 7;\n" +
                "    }\n" +
                "    public boolean isBooleanProperty(){\n" +
                "        return true;\n" +
                "    }\n" +
                "}");
        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("sample.other.pck.SimplePojoMatcher", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "\n" +
                "import javax.annotation.Generated;\n" +
                "\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.Matcher;\n" +
                "import org.hamcrest.Matchers;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SimplePojo.class)\n" +
                "public class SimplePojoMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SimplePojo> beanPropertyMatcher;\n" +
                "\n" +
                "    public SimplePojoMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SimplePojo>(SimplePojo.class);\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withIntProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"intProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withIntProperty(final int value) {\n" +
                "        beanPropertyMatcher.with(\"intProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withShortProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"shortProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withShortProperty(final short value) {\n" +
                "        beanPropertyMatcher.with(\"shortProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withLongProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"longProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withLongProperty(final long value) {\n" +
                "        beanPropertyMatcher.with(\"longProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withDoubleProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"doubleProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withDoubleProperty(final double value) {\n" +
                "        beanPropertyMatcher.with(\"doubleProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withFloatProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"floatProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withFloatProperty(final float value) {\n" +
                "        beanPropertyMatcher.with(\"floatProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withCharProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"charProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withCharProperty(final char value) {\n" +
                "        beanPropertyMatcher.with(\"charProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withByteProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"byteProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withByteProperty(final byte value) {\n" +
                "        beanPropertyMatcher.with(\"byteProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withBooleanProperty(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"booleanProperty\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withBooleanProperty(final boolean value) {\n" +
                "        beanPropertyMatcher.with(\"booleanProperty\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withClass(final Matcher<?> matcher) {\n" +
                "        beanPropertyMatcher.with(\"class\", matcher);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SimplePojoMatcher withClass(final Class value) {\n" +
                "        beanPropertyMatcher.with(\"class\", Matchers.equalTo(value));\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static SimplePojoMatcher isSimplePojo() {\n" +
                "        return new SimplePojoMatcher();\n" +
                "    }\n" +
                "}");
        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }


    @Test
    @DisplayName("No error should be thrown if the processor finds a class generated by itself")
    void testGenerate_NoErrorShouldBeThrownIfTheProcessorFindsAClassGeneratedByItself() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfigurations;\n" +
                "\n" +
                "@MatcherConfigurations(@MatcherConfiguration(\"some.other.pck.SomeGeneratedType\"))\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SomeGeneratedType", "package some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "\n" +
                "import javax.annotation.Generated;\n" +
                "\n" +
                "@Generated(\n" +
                "        value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\",\n" +
                "        date = \"2019-04-19\"\n" +
                ")\n" +
                "@BasedOn(String.class)\n" +
                "public class SomeGeneratedType{\n" +
                "\n" +
                "}");

        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError();
    }

    @Test
    @DisplayName("Matchers should be generated in configured base package")
    void testGenerate_MatchersShouldBeGeneratedInConfiguredBasePackage() {
        // Preparation
        final JavaFileObject configuration = JavaFileObjects.forSourceLines("some.pck.SomeConfiguration", "package some.pck;\n" +
                "\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration.GenerationConfiguration;\n" +
                "import io.github.marmer.annotationprocessing.MatcherConfiguration.GenerationConfiguration.PackageConfiguration;\n" +
                "\n" +
                "@MatcherConfiguration(value = {\"some.other.pck.SomePojo\"}, generation = @GenerationConfiguration(packageConfig= @PackageConfiguration(\"my.base.pck.\")))\n" +
                "public final class SomeConfiguration{\n" +
                "    \n" +
                "}");

        final JavaFileObject javaFileObject = JavaFileObjects.forSourceLines("some.other.pck.SomePojo", "package some.other.pck;\n" +
                "\n" +
                "public interface SomePojo{\n" +
                "    interface InnerInterface {\n" +
                "    }\n" +
                "}");
        final String today = LocalDate.now().toString();
        final JavaFileObject expectedOutput = JavaFileObjects.forSourceString("my.base.pck.sample.other.pck.SomePojoMatcher", "package my.base.pck.some.other.pck;\n" +
                "\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;\n" +
                "import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;\n" +
                "\n" +
                "import javax.annotation.Generated;\n" +
                "\n" +
                "import org.hamcrest.Description;\n" +
                "import org.hamcrest.TypeSafeMatcher;\n" +
                "import some.other.pck.SomePojo;\n" +
                "\n" +
                "@Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "@BasedOn(SomePojo.class)\n" +
                "public class SomePojoMatcher extends TypeSafeMatcher<Object> {\n" +
                "    private final BeanPropertyMatcher<SomePojo> beanPropertyMatcher;\n" +
                "\n" +
                "    public SomePojoMatcher() {\n" +
                "        beanPropertyMatcher = new BeanPropertyMatcher<SomePojo>(SomePojo.class);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    @Override\n" +
                "    public void describeTo(final Description description) {\n" +
                "        beanPropertyMatcher.describeTo(description);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected boolean matchesSafely(final Object item) {\n" +
                "        return beanPropertyMatcher.matches(item);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "        beanPropertyMatcher.describeMismatch(item, description);\n" +
                "    }\n" +
                "\n" +
                "    public static SomePojoMatcher isSomePojo() {\n" +
                "        return new SomePojoMatcher();\n" +
                "    }\n" +
                "\n" +
                "    @Generated(value = \"io.github.marmer.annotationprocessing.core.impl.JavaPoetMatcherGenerator\", date = \"" + today + "\")\n" +
                "    @BasedOn(SomePojo.InnerInterface.class)\n" +
                "    public static class InnerInterfaceMatcher extends TypeSafeMatcher<Object> {\n" +
                "        private final BeanPropertyMatcher<SomePojo.InnerInterface> beanPropertyMatcher;\n" +
                "\n" +
                "        public InnerInterfaceMatcher() {\n" +
                "            beanPropertyMatcher = new BeanPropertyMatcher<SomePojo.InnerInterface>(SomePojo.InnerInterface.class);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void describeTo(final Description description) {\n" +
                "            beanPropertyMatcher.describeTo(description);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        protected boolean matchesSafely(final Object item) {\n" +
                "            return beanPropertyMatcher.matches(item);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        protected void describeMismatchSafely(final Object item, final Description description) {\n" +
                "            beanPropertyMatcher.describeMismatch(item, description);\n" +
                "        }\n" +
                "\n" +
                "        public static InnerInterfaceMatcher isInnerInterface() {\n" +
                "            return new InnerInterfaceMatcher();\n" +
                "        }\n" +
                "    }\n" +
                "}");

        // Execution
        Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(asList(configuration, javaFileObject))
                .processedWith(new MatcherGenerationProcessor())

                // Assertion
                .compilesWithoutError()
                .and()
                .generatesSources(expectedOutput);
    }

    // TODO: marmer 27.04.2019 null generation configuration => default
    // TODO: marmer 27.04.2019 null package configuration => default
}
