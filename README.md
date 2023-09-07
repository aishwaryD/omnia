# Omnia
Test framework to support automation of scenarios spanning multiple platforms

Update:
==================

This is an old codebase with legacy readme/write-up. Please adjust your versions, tech-stack, and modern IDEs. I recommend using JetBrains IDEs (IntelliJ for Java, DataGrip for DB, WebStorm for JS, PyCharm for Python, etc) for better local development capabilities.

Problem Statement:
==================

To design a cross platform test automation framework which empowers individual tests to run on multiple platforms and switch their execution contexts when needed. For example; a test starts its execution on desktop browser, performs few actions, switches to android device, verifies performed actions, switch back to desktop mode, and so on. 

Solution :
==========

The solution leverages selenium and appium to interact with desktop browser and mobile devices respectively.
This enables framework consumer to author scenarios which span multiple platforms/devices.

Project Details:
================

1. Languages Used - JAVA
2. Drivers - Selenium for Web, Appium for Mobile
3. Build Tool - Maven
4. Unit Test Framework - TestNg

Project Structure:
===================
This project is divided into 3 modules:
1. ta-core - This contains all the core classes, models and utils for managing test initialization, logging, validation, test data etc.
2. ta-frontend -  This contains all the drivers(Selenium & Appium) & corresponding usability implementations for various user interactions.
3. ta-tests -  This contains the actual tests.

Upcoming new features/modules:
===================
ta-visual - This custom model will have all the proprietary capabilities of Applitools!
