#!/bin/bash

/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/bin/java -jar jnaerator-0.12-shaded.jar config.jnaerator

jar -xvf torchani.jar

cp edu/uiowa/torchani/*java ../java/edu/uiowa/torchani/.

rm torchani.jar

rm -rf edu


