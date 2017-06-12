[![Build Status](https://travis-ci.org/marmer/dynamic-compilesafe-hamcrest-property-matcher.svg?branch=master)](https://travis-ci.org/marmer/dynamic-compilesafe-hamcrest-property-matcher) 
[![codebeat badge](https://codebeat.co/badges/24fca7c8-55f0-4302-903e-1926475b81ab)](https://codebeat.co/projects/github-com-marmer-dynamic-compilesafe-hamcrest-property-matcher-master)
[![codecov](https://codecov.io/gh/marmer/dynamic-compilesafe-hamcrest-property-matcher/branch/master/graph/badge.svg)](https://codecov.io/gh/marmer/dynamic-compilesafe-hamcrest-property-matcher)

# dynamic-compilesafe-hamcrest-property-matcher
A Way to use hamcrest’s hasProperty Matcher dynamically with compiler errors for not existing properties

## Why
Using Hamcrest's Matcher HasPropertyWithValue, better known as Matchers.hasProperty, is awesome if you don't have created the class you want to test yet. The Problem with that kind of Matcher is, the more test you have matching the same property the harder it is to change the property name.

Here is an example. Lets assume you have a bean with a property named `myFancyProperty`. The following line would match if the value is equal to `Fancy value`.

`assertThat(someBean, hasProperty("myFancyProperty", equalTo("Fancy value")));`

Now assume, you have tests all over your project with a line like this one testinh the `myFancyProperty` and you want to change its name to `myAwesomeProperty`. After renaming the property you may not find all the places in your tests to adjust. Now you only have to wait until the related tests fail to find the other places and fix the property name.

This strategy works pretty well, if you have only changed the property name and the test suite runs fast. Otherwise you'll get your feedback late or it get's really confusing and error prone.

## What
The idea of this lib is to get your feedback allredy at compile time. So your favorite IDE or build tool can show you all the places you have to change as well in a way like the following:

`assertThat(someBean, is(instanceOf(FancyBean.class).andWithMyFancyProperty(equalTo("Fancy Value))));`

You don't have to generate the matchers in advance, because the matchers will be generated at runtime.