/* ************************************************************************

   Copyright:

   License:

   Authors:

************************************************************************ */

/* ************************************************************************

#asset(org.jspresso.framework.testapp.toto.txt)
#use(org.jspresso.framework.gui.remote.RAction)
#use(org.jspresso.framework.gui.remote.RActionField)
#use(org.jspresso.framework.gui.remote.RActionList)
#use(org.jspresso.framework.gui.remote.RBorderContainer)
#use(org.jspresso.framework.gui.remote.RCardContainer)
#use(org.jspresso.framework.gui.remote.RCheckBox)
#use(org.jspresso.framework.gui.remote.RCollectionComponent)
#use(org.jspresso.framework.gui.remote.RColorField)
#use(org.jspresso.framework.gui.remote.RComboBox)
#use(org.jspresso.framework.gui.remote.RComponent)
#use(org.jspresso.framework.gui.remote.RConstrainedGridContainer)
#use(org.jspresso.framework.gui.remote.RContainer)
#use(org.jspresso.framework.gui.remote.RDateField)
#use(org.jspresso.framework.gui.remote.RDecimalComponent)
#use(org.jspresso.framework.gui.remote.RDecimalField)
#use(org.jspresso.framework.gui.remote.RDurationField)
#use(org.jspresso.framework.gui.remote.REvenGridContainer)
#use(org.jspresso.framework.gui.remote.RForm)
#use(org.jspresso.framework.gui.remote.RIcon)
#use(org.jspresso.framework.gui.remote.RImageComponent)
#use(org.jspresso.framework.gui.remote.RIntegerField)
#use(org.jspresso.framework.gui.remote.RLabel)
#use(org.jspresso.framework.gui.remote.RList)
#use(org.jspresso.framework.gui.remote.RNumericComponent)
#use(org.jspresso.framework.gui.remote.RPasswordField)
#use(org.jspresso.framework.gui.remote.RPercentField)
#use(org.jspresso.framework.gui.remote.RSecurityComponent)
#use(org.jspresso.framework.gui.remote.RSplitContainer)
#use(org.jspresso.framework.gui.remote.RTabContainer)
#use(org.jspresso.framework.gui.remote.RTable)
#use(org.jspresso.framework.gui.remote.RTextArea)
#use(org.jspresso.framework.gui.remote.RTextField)
#use(org.jspresso.framework.gui.remote.RTimeField)
#use(org.jspresso.framework.gui.remote.RTree)

#use(org.jspresso.framework.util.gui.Dimension)
#use(org.jspresso.framework.util.gui.CellConstraints)
#use(org.jspresso.framework.util.gui.Font)

#use(org.jspresso.framework.application.frontend.command.remote.RemoteActionCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteAddCardCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteChildrenCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteCloseDialogCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteDialogCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteEnablementCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteFileCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteFileDownloadCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteFileUploadCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteInitCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteInitLoginCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteLocaleCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteLoginCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteMessageCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteOkCancelCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteOpenUrlCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteReadabilityCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteRestartCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteSelectionCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteSortCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteStartCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteValueCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteWorkspaceDisplayCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteWritabilityCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteYesNoCancelCommand)
#use(org.jspresso.framework.application.frontend.command.remote.RemoteYesNoCommand)

************************************************************************ */

/**
 * This is the main application class of your custom application "org.jspresso.framework"
 */
qx.Class.define("org.jspresso.hrsample.startup.qooxdoo.Application",
{
  extend : qx.application.Standalone,



  /*
  *****************************************************************************
     MEMBERS
  *****************************************************************************
  */

  members :
  {
    /**
     * This method contains the initial application code and gets called 
     * during startup of the application
     */
    main : function()
    {
      // Call super class
      this.base(arguments);
      
      qx.Class.patch(qx.ui.form.AbstractField, org.jspresso.framework.patch.MResetValue);
      qx.Class.patch(qx.ui.form.DateField, org.jspresso.framework.patch.MResetValue);
      qx.Class.patch(qx.ui.form.AbstractSelectBox, org.jspresso.framework.patch.MResetValue);

      // Enable logging in debug variant
      if (qx.core.Variant.isSet("qx.debug", "on"))
      {
        // support native logging capabilities, e.g. Firebug for Firefox
        qx.log.appender.Native;
        // support additional cross-browser console. Press F7 to toggle visibility
        qx.log.appender.Console;
      }
      this.test();
      //this.testDesktop();
    },
    
    test : function() {
      var remoteController;
      if (qx.core.Variant.isSet("qx.debug", "on")) {
        remoteController = new qx.io.remote.Rpc(
            "http://localhost:8080/hrsample-webapp/.qxrpc",
            "org.jspresso.hrsample.startup.qooxdoo.QooxdooApplicationStartup"
        );
        remoteController.setCrossDomain(true);
      } else {
        remoteController = new qx.io.remote.Rpc(
            qx.io.remote.Rpc.makeServerURL(),
            "org.jspresso.hrsample.startup.qooxdoo.QooxdooApplicationStartup"
        );
      }
      remoteController.setTimeout(600000);
      
      var qxController = new org.jspresso.framework.application.frontend.controller.qx.DefaultQxController(this, remoteController, "en");
      qxController.start();
    }
  }
});
