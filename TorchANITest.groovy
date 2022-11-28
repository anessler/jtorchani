//******************************************************************************
//
// Title:       Force Field X.
// Description: Force Field X - Software for Molecular Biophysics.
// Copyright:   Copyright (c) Michael J. Schnieders 2001-2021.
//
// This file is part of Force Field X.
//
// Force Field X is free software; you can redistribute it and/or modify it
// under the terms of the GNU General Public License version 3 as published by
// the Free Software Foundation.
//
// Force Field X is distributed in the hope that it will be useful, but WITHOUT
// ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
// FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
// details.
//
// You should have received a copy of the GNU General Public License along with
// Force Field X; if not, write to the Free Software Foundation, Inc., 59 Temple
// Place, Suite 330, Boston, MA 02111-1307 USA
//
// Linking this library statically or dynamically with other modules is making a
// combined work based on this library. Thus, the terms and conditions of the
// GNU General Public License cover the whole combination.
//
// As a special exception, the copyright holders of this library give you
// permission to link this library with independent modules to produce an
// executable, regardless of the license terms of these independent modules, and
// to copy and distribute the resulting executable under terms of your choice,
// provided that you also meet, for each linked independent module, the terms
// and conditions of the license of that module. An independent module is a
// module which is not derived from or based on this library. If you modify this
// library, you may extend this exception to your version of the library, but
// you are not obligated to do so. If you do not wish to do so, delete this
// exception statement from your version.
//
//******************************************************************************

import java.nio.DoubleBuffer
import com.sun.jna.NativeLong
import com.sun.jna.NativeLibrary
import static java.lang.String.format
import edu.uiowa.torchani.TorchANILibrary
import edu.uiowa.torchani.TorchANIUtils
import static edu.uiowa.torchani.TorchANILibrary.ANIEnergyAndGradient

/**
 * The TorchANITest script predicts the energy and gradient of a structure based on the Torch ANI model.
 *
 * @author Aaron J. Nessler
 * <br>
 * Usage:
 * <br>
 * ffxc test.TorchANITest &lt;filename&gt;
 */
public class TorchANITest{

    /**
     * Execute the script.
     */
    public static void  main(String[] args) {

        String libTorchHome = System.getenv("LIBTORCH_HOME")
        System.out.println("Lib Torch Home: " + libTorchHome);
        NativeLibrary.addSearchPath("libc10", libTorchHome + "/lib");

        TorchANIUtils.init();

        System.out.println(" ANI Dir: " + TorchANIUtils.getLibDirectory());

        int numAtoms = 5;
        double[] coordinates = new double[]{0.03192167, 0.00638559, 0.01301679,
                                    -0.83140486, 0.39370209, -0.26395324,
                                    -0.66518241, -0.84461308, 0.20759389,
                                    0.45554739, 0.54289633, 0.81170881,
                                    0.66091919, -0.16799635, -0.91037834};
        int[] species = new int[]{6, 1, 1, 1, 1};
        String pathToTorch = new String("ANI2xgradients.pt");
        double energy;
        double[] gradient = new double[numAtoms * 3];
        System.out.println(" Structure has been loaded. \n Start Torch ANI.")
        NativeLong numA = new NativeLong(numAtoms);
        try{
           energy = ANIEnergyAndGradient(pathToTorch, numA, species, coordinates, gradient)
           System.out.println(format(" ANI Energy: %9.4f", energy));
           System.out.println(format(" ANI Gradient:"));
           for(int i = 0; i < numAtoms; i++){
               for(int j = 0; j < 3; j++){
                   System.out.println(format(" %9.4f ", gradient[i * 3 + j]))
               }
           }
        }catch (Exception e){
           System.out.println(" Error running Energy.")
           System.out.println(e.printStackTrace())
        }
    }
}
