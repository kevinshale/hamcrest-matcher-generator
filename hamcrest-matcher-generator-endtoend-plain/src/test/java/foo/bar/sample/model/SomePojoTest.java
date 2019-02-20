package foo.bar.sample.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static foo.bar.sample.model.ParentPojoMatcher.isParentPojo;
import static foo.bar.sample.model.SomePojoMatcher.InnerClassMatcher.InnerInnerPojoMatcher.isInnerInnerPojo;
import static foo.bar.sample.model.SomePojoMatcher.isSomePojo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class SomePojoTest {
    @Test
    @DisplayName("Generated matchers match properties as expected")
    void testMatchers_GeneratedMatchersMatchPropertiesAsExpected()
            throws Exception {
        // Preparation
        final SomePojo somePojo = new SomePojo();
        somePojo.setPojoField("pojoFieldValue");
        somePojo.setParentField("someParentFieldValue");

        // Assertion
        assertThat(somePojo, isSomePojo()
                .withClass(SomePojo.class)
                .withParentField("someParentFieldValue")
                .withParentField(is(equalTo("someParentFieldValue")))
                .withPojoField("pojoFieldValue")
                .withPojoField(is(equalTo("pojoFieldValue")))
        );
    }

    @Test
    @DisplayName("Matchers of parent classes should work fine as well")
    void testMatchers_MatchersOfParentClassesShouldWorkFineAsWell()
            throws Exception {
        // Preparation
        final SomePojo somePojo = new SomePojo();
        somePojo.setPojoField("pojoFieldValue");
        somePojo.setParentField("someParentFieldValue");

        // Assertion
        assertThat(somePojo, isParentPojo()
                .withClass(SomePojo.class)
                .withParentField("someParentFieldValue")
                .withParentField(is(equalTo("someParentFieldValue")))
        );
    }

    @Test
    @DisplayName("Matcher for innerclasses should work")
    void testMatchers_MatcherForInnerclassesShouldWork()
            throws Exception {
        // Preparation
        final SomePojo.InnerClass.InnerInnerPojo innerInnerPojo = new SomePojo.InnerClass.InnerInnerPojo("someValue");

        // Execution

        // Assertion
        assertThat(innerInnerPojo, isInnerInnerPojo()
                .withSomeField("someValue"));
    }
}