# Java Wrappers for the Torch ANI C API.
Java Wrappers for the Torch ANI C API automatically generated using Jnaerator.

Author: Aaron Nessler, Mitchell Hermon, Logan Martin
Email: aaron-nessler@uiowa.edu

Based on OpenMM C API by: Michael J Schnieders
Email: michael-schnieders@uiowa.edu

## Introduction	
This project contains Java Wrappers for the Torch ANI C API, which are automatically generated using Jnaerator.

## Generation of the Java Torch ANI Wrappers using Jnaerator

The following Jnaerator command can then be used: 

	java -jar jnaerator-0.12-shaded.jar config.jnaerator

where the contents of "config.jnaerator" are given by:

	-limitComments

	-runtime JNA

	-direct

	-skipDeprecated

	-mode Jar

	-jar torchani.jar

	-package edu.uiowa.jtorchani

	-library TorchANI
	../include/TorchANIWrapper.h

The configuration file contains the various flags needed to set up generation of the wrappers as well as information about where to locate the Torch ANI header files. Some Important flags include:

* -runtime sets the runtime library that will be used to generate the wrapper classes, in this case JNA

* -direct tells the Jnaerator to use the fastest direct call convention for library generation

* -skipDeprecated skips the generation of any deprecated members

* -mode specifies the output mode for the jnaerator

* -package sets the java package where all the generated output will reside (our package is called simtk.openmm)

* -library sets the name of the output library. Importantly, after the library flag you must specifiy (i.e. set the path to) C API header files.

For additional documentation on flag options available to edit the configuration file visit Jnaerator Wiki:
https://github.com/nativelibs4java/JNAerator/wiki/Command-Line-Options-And-Environment-Variables

## Using the JTorchANI Library

To use the Java Torch ANI Wrappers, please first initialize the library using the TorchANIUtils class:
	
      TorchANIUtils.init();

This will extract the OpenMM binary libraries (for Cuda 10.1) from the torchani-fat.jar file to a temporary directory and configure JNA to find them. 
