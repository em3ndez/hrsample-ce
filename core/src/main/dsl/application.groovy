import org.jspresso.contrib.sjs.domain.Domain;
import org.jspresso.contrib.sjs.front.Front;
import org.jspresso.contrib.sjs.common.ManageModule;

def domainBuilder = new Domain()

domainBuilder.Domain(projectName:'hrsample', mute:true, includeDirectory:project.properties['srcDir']) {
  namespace('org.jspresso.hrsample') {
    include('model.sjs')
  }
}
if(!domainBuilder.isOK()) {
  println domainBuilder.getErrorDomain()
  fail('SJS defined domain is invalid:\n' + domainBuilder.getErrorDomain())
}

def frontendBuilder = new Front(domainBuilder.getReferenceDomain())
frontendBuilder.Front(){
  namespace('org.jspresso.hrsample'){
    view {
      include('view.sjs')
      spec('mobile') {
        include('view-mobile.sjs')
      }
    }
    frontend {
      include('frontend.sjs')
      spec('mobile') {
        include('frontend-mobile.sjs')
      }
    }
    backend {
      include('backend.sjs')
      spec('mobile') {
        include('backend-mobile.sjs')
      }
    }
  }
}
if(frontendBuilder.getNbrError() != 0) {
  println frontendBuilder.getError()
  fail('SJS defined frontend / views is invalid:\n' + frontendBuilder.getError())
}

domainBuilder.writeDomainFile(project.properties['outputDir'],project.properties['modelOutputFileName'])

frontendBuilder.writeOutputFile('backend',project.properties['outputDir'],project.properties['backOutputFileName'])
frontendBuilder.writeOutputFile('backend/mobile',project.properties['outputDir'],
    'mobile-'+project.properties['backOutputFileName'])
frontendBuilder.writeOutputFile('view',project.properties['outputDir'],project.properties['viewOutputFileName'])
frontendBuilder.writeOutputFile('view/mobile',project.properties['outputDir'],
    'mobile-'+project.properties['viewOutputFileName'])
frontendBuilder.writeOutputFile('frontend',project.properties['outputDir'],project.properties['frontOutputFileName'])
frontendBuilder.writeOutputFile('frontend/mobile',project.properties['outputDir'],
    'mobile-'+project.properties['frontOutputFileName'])

//Export as module
ManageModule manageModule = new ManageModule()
manageModule.exportModule('hrsample',domainBuilder,frontendBuilder,project.properties['outputDir'])

// Uml output (see colors at http://www.graphviz.org/doc/info/colors.html)
try {

  String umlPath = project.properties['outputDir'] + "/../../";
  new File(umlPath + "umlMain.dot").write(domainBuilder.getDotGraph(
          ['Traceable', 'ContactInfo', 'Nameable'],
          ['@Employee', '@Company', '@User'],
          false, // No computed fields
          [gold           : ['Company', 'Departement', 'Team'],
           lightgoldenrod1: ['Employee'],
          ]))


}
catch (Exception ignored) {
  //To avoid problems on non windows platform or if GraphWiz is not installed
  println ignored.message
  println "GraphViz not available ?"
}

println "Build finished."
