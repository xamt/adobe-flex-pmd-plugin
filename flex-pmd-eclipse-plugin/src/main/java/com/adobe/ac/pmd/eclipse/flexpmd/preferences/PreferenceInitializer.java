/**
 *    Copyright (c) 2009, Adobe Systems, Incorporated
 *    All rights reserved.
 *
 *    Redistribution  and  use  in  source  and  binary  forms, with or without
 *    modification,  are  permitted  provided  that  the  following  conditions
 *    are met:
 *
 *      * Redistributions  of  source  code  must  retain  the  above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      * Redistributions  in  binary  form  must reproduce the above copyright
 *        notice,  this  list  of  conditions  and  the following disclaimer in
 *        the    documentation   and/or   other  materials  provided  with  the
 *        distribution.
 *      * Neither the name of the Adobe Systems, Incorporated. nor the names of
 *        its  contributors  may be used to endorse or promote products derived
 *        from this software without specific prior written permission.
 *
 *    THIS  SOFTWARE  IS  PROVIDED  BY THE  COPYRIGHT  HOLDERS AND CONTRIBUTORS
 *    "AS IS"  AND  ANY  EXPRESS  OR  IMPLIED  WARRANTIES,  INCLUDING,  BUT NOT
 *    LIMITED  TO,  THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 *    PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER
 *    OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,  INCIDENTAL,  SPECIAL,
 *    EXEMPLARY,  OR  CONSEQUENTIAL  DAMAGES  (INCLUDING,  BUT  NOT  LIMITED TO,
 *    PROCUREMENT  OF  SUBSTITUTE   GOODS  OR   SERVICES;  LOSS  OF  USE,  DATA,
 *    OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *    LIABILITY,  WHETHER  IN  CONTRACT,  STRICT  LIABILITY, OR TORT (INCLUDING
 *    NEGLIGENCE  OR  OTHERWISE)  ARISING  IN  ANY  WAY  OUT OF THE USE OF THIS
 *    SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.adobe.ac.pmd.eclipse.flexpmd.preferences;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.osgi.framework.Bundle;

import com.adobe.ac.pmd.eclipse.FlexPMDPlugin;

public class PreferenceInitializer extends AbstractPreferenceInitializer
{

   @Override
   public void initializeDefaultPreferences()
   {
      final IPreferenceStore preferenceStore = FlexPMDPlugin.getDefault().getPreferenceStore();

      preferenceStore.setDefault( PreferenceConstants.USE_BUNDLED_FLEXPMD,
                                  true );

      preferenceStore.setDefault( PreferenceConstants.COMMAND_LINE_INSTALLATION_PATH,
                                  getDefaultPmdRuntimeLocation() );

      preferenceStore.setDefault( PreferenceConstants.JAVA_COMMAND_LINE_ARGUMENTS,
                                  "-Xmx256m" );

      preferenceStore.setDefault( PreferenceConstants.CPD_MINIMUM_TOKENS,
                                  25 );
   }

   private String getDefaultPmdRuntimeLocation()
   {
      Bundle bundle = FlexPMDPlugin.getDefault().getBundle();
      URL flexPmdDefaultPath = FileLocator.find( bundle,
                                                 new Path( "/flexPmdRuntime" ),
                                                 null );

      try
      {
         flexPmdDefaultPath = FileLocator.toFileURL( flexPmdDefaultPath );
      }
      catch ( IOException e )
      {
         FlexPMDPlugin.getDefault()
                      .logError( "Problem accessing default FlexPMD runtime installation folder.",
                                 e );
      }

      return new Path( flexPmdDefaultPath.getFile() ).toString();
   }
}