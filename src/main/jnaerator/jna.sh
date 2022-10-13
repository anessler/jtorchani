#!/bin/bash

/Library/Java/JavaVirtualMachines/jdk1.8.0_261.jdk/Contents/Home/bin/java -jar jnaerator-0.12-shaded.jar config.jnaerator

jar -xvf openmm.jar

cp edu/uiowa/jopenmm/*java ../java/edu/uiowa/jopenmm/.

rm openmm.jar

rm -rf edu


