workspace('masterdata.workspace',
    icon:'masterdata-48x48.png') {
  nodeModule('masterdata.geography.module',
      icon:'geography-48x48.png') {
    filterModule('masterdata.cities.module',
        component:'City',
        startup:'filterModuleStartup')
  }
}

workspace('employees.workspace',
    icon:'people-48x48.png',
    grantedRoles:[
        'administrator',
        'staff-manager'
    ]) {
  filterModule('employees.module',
      icon:'employees-48x48.png',
      component:'Employee',
      startup:'filterModuleStartup',
      pageSize:4)
}

workspace('organization.workspace',
    icon:'structure-48x48.png',
    grantedRoles:[
        'administrator',
        'organization-manager'
    ]) {
  filterModule('companies.module',
      icon:'company-48x48.png',
      component:'Company',
      detailView:'Company.module.view',
      startup:'filterModuleStartup')
}

workspace('departments.workspace',
    icon:'department-48x48.png') {
  filterModule('departments.module',
      icon:'department-48x48.png',
      component:'Department')
}

controller 'hrsample.name',
    icon:'people-48x48.png',
    context:'hrsample',
    language:'en',
    startup:'startupHrsampleAction',
    workspaces:[
        'organization.workspace',
        'employees.workspace',
        'masterdata.workspace'
    ]

