template 'form',
  parent:'decoratedView',
  labelsPosition:'ABOVE',
  columnCount:2
  
template 'table',
  parent:'decoratedView'

form 'Traceable.pane',
  model:'Traceable',
  description:'traceable.editing',
  fields:['createTimestamp','lastUpdateTimestamp']

form 'Company.pane',
  labelsPosition:'ASIDE',
  fields:['name','contact.address','contact.city','contact.phone','contact.email'],
  width:[name:2],
  description:'company.editing'

treeNode 'Department-teams.treeNode',
  render:'ouId',
  actionMap:'masterDetail'
  
treeNode 'Department-employees.treeNode',
  render:'name',
  actionMap:'masterDetail'
      
treeNode 'Company-employees.treeNode',
  render:'name',
  actionMap:'masterDetail'
            
treeNode 'Company-departments.treeNode',
  render:'ouId',
  actionMap:'masterDetail'

tree('Company.tree',
  render:'name',
  icon:'structure-48x48.png') {
  subTree('Company-employees.treeNode')
  subTree('Company-departments.treeNode') {
    subTree('Department-teams.treeNode')
    //subTree('Department-employees.treeNode')
  }
}

tabs 'Company.tab.pane',
  views:['Company.pane','Company.tree','Traceable.pane']

table 'Company-departments.table',
  actionMap:'masterDetail'

table 'Department-teams.table',
  column:['ouId','name','manager'],
  actionMap:'masterDetail'

action('addFromList',
  parent:'lovOkFrontAction') {
  next(parent:'addAnyToMasterFrontAction')
}

table('Team-teamMembers.table') {
  actionMap {
    actionList('EDIT'){
      action(parent:'lovAction',
        custom:[
          autoquery:false,
          entity:'Employee',
          initializationMapping:['company':'company'],
          okAction_ref:'addFromList'
        ]
      )
      action(ref:'removeAnyCollectionFromMasterFrontAction')
    }
  }
}

split_vertical('Departments.and.teams.view',
  cascadingModels:true,
  top:'Company-departments.table') {
  bottom {
    split_horizontal (
      left:'Department-teams.table',
      right:'Team-teamMembers.table',
      cascadingModels:true
    )
  }
}

split_vertical 'Company.organization.view',
  model:'Company',
  top:'Company.tab.pane',
  bottom:'Departments.and.teams.view'

image 'Employee-photo.pane',
  parent:'decoratedView',
//  model:'Employee-photo',
  actionMap:'binaryPropertyActionMap'

form 'Employee.component.pane',
  columnCount:3
    
split_horizontal 'Employee.pane',
  left:'Employee.component.pane',
  right:'Employee-photo.pane'

table 'Employee-events.table',
  actionMap:'masterDetail'

propertyView 'Event-text.pane',
  name:'text',
  parent:'decoratedView',
  actionMap:'binaryPropertyActionMap'

actionMap('save-reload-module-am') {
  actionList('FILE',
    actions:[
      'saveModule',
      'reloadModule'
    ]
  )
}

bean('Company.report',
  parent:'abstractReportDescriptor',
  custom:[
    reportDesignUrl:'classpath:org/jspresso/hrsample/report/Company.jasper'
  ]) {
  list('propertyDescriptors') {
    bean(class:'org.jspresso.framework.model.descriptor.basic.BasicStringPropertyDescriptor',
      name:'title'
    )             
  }
}

bean('Company.chart',
  class:'org.jspresso.hrsample.chart.CompanyChart',
  custom:[
    url:'classpath:com/fusioncharts/FCF_Column3D.swf',
    title:'company.chart'
  ]
)

actionMap('Company-module-am'){
  actionList('FILE'){
    action(ref:'saveModule')
    action(ref:'reloadModule')
    action(parent:'staticReportAction',
      custom:[
        reportDescriptor_ref:'Company.report'
      ]
    )
    action(parent:'chartAction',
      //description:'company.chart',
      custom:[
        chartDescriptor_ref:'Company.chart'
      ]
    )
  }
}

form 'City.module.view',
  labelsPosition:'ABOVE',
  actionMap:'save-reload-module-am',
  columnCount:1

split_vertical('Employee.module.view',
  actionMap:'save-reload-module-am',
  top:'Employee.pane') {
  bottom {
    split_horizontal(
      left:'Employee-events.table',
      right:'Event-text.pane',
      cascadingModels:true)
  }
}

split_vertical 'Company.module.view',
  parent:'Company.organization.view',
  actionMap:'Company-module-am'
  
messageSource(basenames:'org.jspresso.hrsample.i18n.Messages')
  