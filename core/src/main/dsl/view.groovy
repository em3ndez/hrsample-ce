template 'form',
    parent:'decoratedView',
    labelsPosition:'ABOVE',
    columnCount:2

template 'table',
    parent:'decoratedView'

form('Traceable.pane',
    model:'Traceable',
    description:'traceable.editing',
    fields:[
      'createTimestamp',
      'lastUpdateTimestamp'
    ])

form('Company.pane',
    labelsPosition:'ASIDE',
    fields:[
      'name',
      'contact.address',
      'contact.city',
      'contact.phone',
      'contact.email'
    ],
    widths:[name:2],
    description:'company.editing')

treeNode('Department-teams.treeNode',
    rendered:'ouId',
    actionMap:'masterDetailActionMap')

treeNode('Department-employees.treeNode',
    rendered:'name',
    actionMap:'masterDetailActionMap')

treeNode('Company-employees.treeNode',
    rendered:'name',
    actionMap:'masterDetailActionMap')

treeNode('Company-departments.treeNode',
    rendered:'ouId',
    actionMap:'masterDetailActionMap')

tree('Company.tree',
    rendered:'name',
    preferredHeight:200,
    icon:'structure-48x48.png') {
      subTree('Company-employees.treeNode')
      subTree('Company-departments.treeNode') { subTree('Department-teams.treeNode') //subTree('Department-employees.treeNode')
      }
    }

tabs('Company.tab.pane',
    views:[
      'Company.pane',
      'Company.tree',
      'Traceable.pane',
      'translations.table'
    ])

table('Company-departments.table',
    actionMap:'masterDetailActionMap', columnReordering:false) {
      columns {
        propertyView name:'ouId'
        propertyView name:'teamCount'
        propertyView name:'name'
        propertyView name:'manager'
        propertyView name:'contact'
        propertyView name:'createTimestamp'
        propertyView name:'lastUpdateTimestamp'
      }
    }

table('Department-teams.table',
    columns:['ouId', 'name', 'manager'],
    actionMap:'masterDetailActionMap')

action('addFromList',
    parent:'lovOkFrontAction') { next(parent:'addAnyToMasterFrontAction') }

listView('Team-teamMembers.list', preferredWidth:300) {
  actionMap {
    actionList('EDIT'){
      action(parent:'pickupAndAddAnyFrontAction',
          custom:[
            entityDescriptor_ref:'Employee',
            initializationMapping:['company':'company'],
          ]
          )
      action(ref:'removeAnyCollectionFromMasterFrontAction')
    }
  }
}

split_vertical('Company.departments.and.teams.view',
    cascadingModels:true,
    top:'Company-departments.table') {
      bottom {
        border (
            center:'Department-teams.table',
            east:'Team-teamMembers.list',
            cascadingModels:true
            )
      }
    }

border('Company.organization.view',
    model:'Company',
    north:'Company.tab.pane',
    center:'Company.departments.and.teams.view')

image('Employee-photo.pane',
    parent:'decoratedView',
    actionMap:'binaryPropertyActionMap',
    preferredWidth:400)


form('Employee.component.pane',
    columnCount:3,
    description:'htmlDescription',
    labelsPosition:'ASIDE') {
      fields {
        propertyView name:'name'
        propertyView name:'firstName'
        enumerationPropertyView name:'gender', radio:true, orientation:'HORIZONTAL'
        propertyView name:'birthDate'
        propertyView name:'age'
        propertyView name:'ssn'
        propertyView name:'salary'
        propertyView name:'contact'
        propertyView name:'married'
        propertyView name:'preferredColor'
        propertyView name:'photo'
        propertyView name:'company'
        propertyView name:'createTimestamp'
        propertyView name:'lastUpdateTimestamp'
      }
    }

bean('employeeCardSelector', class:'org.jspresso.hrsample.view.EmployeeCardSelector')

form('Employee.married.pane', fields:['name', 'firstName'])

form('Employee.notmarried.pane', columnCount:3, fields:['firstName', 'name', 'age'])

form('Employee.filter.pane', columnCount:8, fields:[
  'firstName',
  'name',
  'gender',
  'salary',
  'birthDate'
], widths:['firstName':4,'name':4,'gender':4,'salary':4,'birthDate':4])

form('Department.filter.pane', columnCount:8, fields:[
    'ouId',
    'name',
    'manager.gender'
], widths:['firstName':4,'name':4,'gender':4,'salary':4,'birthDate':4])

border('Employee.border.pane',
    center:'Employee.component.pane') {
      north {
        border {
          east {
            image model:'Employee-genderImageUrl',
                scrollable:false
          }
        }
      }
      //      south {
      //        basicCardView(selector:'employeeCardSelector',
      //           views:['MARRIED':'Employee.married.pane','NOT_MARRIED':'Employee.notmarried.pane'])
      //      }
    }

split_horizontal('Employee.pane',
    left:'Employee.border.pane',
    right:'Employee-photo.pane')

table('Employee-events.table',
    selectionMode:'SINGLE_INTERVAL_CUMULATIVE_SELECTION') {
      actionMap(parents:['masterDetailActionMap']) {
        actionList('ORGANIZE') {
          action ref:'moveBottomFrontAction'
          action ref:'moveDownFrontAction'
          action ref:'moveUpFrontAction'
          action ref:'moveTopFrontAction'
        }
      }
    }

propertyView('Event-text.pane',
    name:'text',
    parent:'decoratedView',
    actionMap:'binaryPropertyActionMap')

bean('Company.report',
    parent:'abstractReportDescriptor',
    custom:[
      reportDesignUrl:'classpath:org/jspresso/hrsample/report/Company.jasper',
      renderedProperties:['title']]) {
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
    action(ref:'saveModuleObjectFrontAction')
    action(ref:'reloadModuleObjectFrontAction')
    action(parent:'staticReportAction',
        custom:[
          reportDescriptor_ref:'Company.report'
        ]
        )
    action(parent:'chartAction',
        description:'company.chart',
        custom:[
          chartDescriptor_ref:'Company.chart'
        ]
        )
    action(parent:'editSelectedComponentAction', custom:[viewDescriptor_ref:'Company.dialog.view'])
    //action(class:'test.TestFrontAction', icon:'company-48x48.png')
    //action(class:'org.jspresso.framework.application.frontend.action.FrontendAction', icon:'company-48x48.png') { wrapped(class:'test.TestBackAction') }
  }
}

tabs('Company.dialog.view', parent:'Company.tab.pane')

form('City.module.view',
    labelsPosition:'ABOVE',
    actionMap:'beanModuleActionMap',
    columnCount:1)

split_vertical('Employee.module.view',
    actionMap:'beanModuleActionMap',
    top:'Employee.pane') {
      bottom {
        split_horizontal(
            left:'Employee-events.table',
            right:'Event-text.pane',
            cascadingModels:true)
      }
    }

border('Company.module.view',
    parent:'Company.organization.view',
    actionMap:'Company-module-am')

table('Employee.table.view', parent:'filterableBeanCollectionModuleView', selectionMode:'MULTIPLE_INTERVAL_CUMULATIVE_SELECTION') {
  columns {
    propertyView name:'name'
    propertyView name:'firstName'
    propertyView name:'gender'
    propertyView name:'birthDate'
    propertyView name:'age'
    propertyView name:'ssn'
    propertyView name:'salary'
    propertyView name:'contact'
    propertyView name:'married'
    propertyView name:'preferredColor'
    propertyView name:'photo'
    propertyView name:'company'
    propertyView name:'createTimestamp'
    propertyView name:'lastUpdateTimestamp'
  }
}

messageSource(basenames:'org.jspresso.hrsample.i18n.Messages')
