paramSet 'gender', enumName: 'GENDER', mandatory: true, queryMultiselect: true,
    valuesAndIcons: ['M': 'male.png',
                     'F': 'female.png'],
    defaultValue: 'M'

Interface('Nameable') { string_64 'name', mandatory: true, translatable: true}

Interface('Traceable',
    interceptors: 'TraceableLifecycleInterceptor',
    icon: 'traceable.png',
    uncloned: ['createTimestamp',
               'lastUpdateTimestamp']) {
  date_time 'createTimestamp', timeZoneAware: true, readOnly: true
  date_time 'lastUpdateTimestamp', timeZoneAware: true, readOnly: true
}

Entity('City',
    extend: 'Nameable',
    extension: 'CityExtension',
    services: ['CityService': 'CityServiceDelegate'],
    icon: 'city.png',
    pageSize: 4,
    toString: 'name',
    description: '',
    rendered: ['name', 'zip', 'longitude', 'latitude']) {

  string_10 'zip', upperCase: true, truncate: true
  decimal 'longitude', maxValue: 190, minValue: -190, maxFractionDigit: 4
  decimal 'latitude', maxValue: 190, minValue: -190, maxFractionDigit: 4

  java 'route', class: 'org.jspresso.framework.util.gui.map.Route', maxLength: 2048, computed: true, cacheable: true
  string 'mapContent', maxLength: 4096, computed: true, cacheable: true
  set 'neighbours', ref: 'City', reverse: 'City-neighbours'

  color 'longitudeBackground', computed: true
  color 'latitudeBackground', computed: true

  string 'cardSelector', computed: true
}

Component('ContactInfo',
  extension:'ContactInfoExtension') {

  string_256 'address'
  reference 'city', ref: 'City'
  string_32 'phone'
  string_128 'email', regex: '[\\w\\-\\.]*@[\\w\\-\\.]*', regexSample: 'contact@acme.com'

  html 'phoneAsHtml', computed:true, i18nNameKey:'phone'
}

Entity('Event', extend: 'Traceable') {
  html 'text', maxLength: 204800, id: 'Event-text'
  reference 'employee', ref: 'Employee', reverse: 'Employee-events'
}

Entity('Employee',
    extend: ['Nameable', 'Traceable'],
    interceptors: 'EmployeeLifecycleInterceptor',
    extension: 'EmployeeExtension',
    processor: 'EmployeePropertyProcessors',
    services: [EmployeeService: 'EmployeeServiceDelegate'],
    serviceBeans: ['EmployeeService': 'EmployeeServiceDelegateBean'],
    icon: 'male.png',
    uncloned: ['managedOu', 'ssn'],
    ordering: ['name': 'ASCENDING'],
    toString: 'fullName',
    toHtml: 'htmlDescription',
    autoComplete: 'name',
    pageSize: 20,
    rendered: ['name',
               'firstName',
               'gender',
               'birthDate',
               'age',
               'ssn',
               'salary',
               'contact',
               'married',
               'preferredColor',
               'photo',
               'company',
               'createTimestamp',
               'lastUpdateTimestamp'],
    queryable: ['name',
                'firstName',
                'gender',
                'birthDate',
                'company',
                'managedOu.manager']) {
  string_32 'firstName', mandatory: true, processors: 'FirstNameProcessor', translatable: true
  string_10 'ssn', regex: "[\\d]{10}", regexSample: '0123456789', unicityScope: 'empSsn'
  date 'birthDate', processors: 'BirthDateProcessor'
  date 'hireDate'
  enumeration 'gender', paramSets: 'gender'
  color 'preferredColor'
  bool 'married'
  decimal 'salary', minValue: 0, maxValue: 10000000, usingBigDecimal: true
  image 'photo', maxLength: 1048576, id: 'Employee-photo', fileFilter: ['images': ['.jpg']], fileName: 'photo.jpg',
      processors: 'PhotoProcessor', scaledHeight: 200
  image 'signature', maxLength: 1048576, id: 'Employee-signature', fileFilter: ['images': ['.png']],
      fileName: 'signature.png'
  password 'password', maxLength: 32
  reference 'contact', ref: 'ContactInfo', id: 'contact'
  list 'events', composition: true, ref: 'Event'
  list 'alternativeEvents', composition: true, ref: 'Event', nullElement: true
  set 'teams', ref: 'Team'
  list 'alternativeContacts', ref: 'ContactInfo', nullElement: true
  reference 'company', ref: 'Company', mandatory: true, reverse: 'Company-employees'
  reference 'managedOu', ref: 'OrganizationalUnit', reverse: 'OrganizationalUnit-manager'
  integer 'age', minValue: 0, maxValue: 150, sqlName: 'YEAR(current_date) - YEAR(BIRTH_DATE)', computed: true, cacheable: true
  imageUrl 'genderImageUrl', maxLength: 512, id: 'Employee-genderImageUrl', computed: true, scaledHeight: 100
  string_512 'fullName', computed: true, i18nNameKey:'name', id:'Employee-fullname'
  html 'htmlDescription', computed: true
  set 'users', ref:'User', reverse:'User-employee'
  enumeration 'language', enumName:'language', values:['fr', 'en'], defaultValue:'fr'

  reference 'bonus', ref:'EncryptedDecimal', readOnly:true
}

Component('EncryptedDecimal', extension:'EncryptedDecimalExtension', toString:'decryptedValue') {
  decimal    'decryptedValue', computed:true, delegateWritable:true, thousandsGrouping:false
  binary     'encryptedValue'
}

Entity('Company',
    extend: ['Nameable', 'Traceable', 'IAttachmentHolder'],
    extension: 'CompanyExtension',
    icon: 'company.png',
    rendered: ['name',
               'contact',
               'createTimestamp',
               'lastUpdateTimestamp'],
    queryable: ['name',
                'contact.city',
                'contact.city.zip']) {
  refId 'contact', id: 'contact'
  set 'departments', composition: true, ref: 'Department'
  set 'employees', composition: true, ref: 'Employee'

  integer 'workforce', computed: true

  reference 'budget', ref:'EncryptedDecimal', readOnly:true
}

Entity('OrganizationalUnit',
    extend: ['Traceable'],
    purelyAbstract: true,
    processor: 'OrganizationalUnitPropertyProcessors',
    extension: 'OrganizationalUnitExtension') {
  string_6 'ouId', regex: "[A-Z]{2}-[\\d]{3}", regexSample: 'AB-123', mandatory: true
  refId 'contact', id: 'contact'
  reference 'manager', ref: 'Employee', mandatory: false, processors: ['ManagerProcessor'],
      initializationMapping: ['company': 'company']
  reference 'company', ref: 'Company', computed: true
  html 'htmlDescription', computed: true
  set 'members', ref: 'Employee', computedFurther: true
  string_16 'filterOnlyProp', filterOnly: true
}

Entity('Department',
    extend: ['Nameable', 'OrganizationalUnit'],
    extension: 'DepartmentExtension',
    icon: 'department.png',
    toHtml: 'htmlDescription', ordering: ['name': 'ASCENDING'],
    rendered: ['ouId',
               'name',
               'manager',
               'contact',
               'createTimestamp',
               'lastUpdateTimestamp']) {
  reference 'company', ref: 'Company', reverse: 'Company-departments', mandatory: true
  set 'teams', composition: true, ref: 'Team'
  set 'members', ref: 'Employee', computed: true
  integer 'teamCount', computed: true, sqlName: '(SELECT COUNT(T.ID) FROM TEAM T WHERE T.DEPARTMENT_ID=ID)'
}

Entity('Team',
    extend: ['Nameable', 'OrganizationalUnit'],
    icon: 'team.png',
    toHtml: 'htmlDescription', ordering: ['name': 'ASCENDING'],
    rendered: ['ouId',
               'name',
               'manager',
               'contact']) {
  reference 'department', ref: 'Department', mandatory: true, reverse: 'Department-teams'
  set 'members', ref: 'Employee', reverse: 'Employee-teams'
}

Entity('Preferences') {
  string_2048 'preferenceValue';
  string_128 'preferencePath';
}

Component('Link', extend: 'Nameable', rendered: 'name') {
  reference 'parent', ref: 'Link', mandatory: false
  set 'children', ref: 'Link'
}

Entity('User',
  extend: ['Traceable'],
  extension:'UserExtension',
  rendered:['login', 'employee', 'createTimestamp', 'lastUpdateTimestamp'],
  queryable: ['login', 'employee.name'],
  services:['UserService':'UserServiceDelegate']) {

  string_64 'login', mandatory:true, unicityScope:'community'
  password  'password', maxLength:32

  set 'roles', ref:'Role'

  // relations
  reference 'employee', ref:'Employee', i18nNameKey:'org.jspresso.hrsample.model.Employee'

  // computed
  reference 'mainRole', ref:'Role', computed:true
}

Entity('Role',
  extend:['Traceable'],
  rendered:['roleId', 'createTimestamp', 'lastUpdateTimestamp'],
  icon:'employees.png') {

  string_30 'roleId', unicityScope:'roleId'

  set 'users', ref:'User', reverse:'User-roles'
}

Entity('Attachment',
        extend: 'Traceable',
        extension: 'AttachmentExtension',
        toString: 'name',
        icon: 'attachment.svg',
        pageSize: 30) {

  string_256 'name'
  string_256 'description'
  binary 'attached', maxLength: 5242880 // 5Mo
  string_256 'attachedBy'
  integer 'size', usingLong: true

  // computed
  string 'url', computed: true
  string 'sizeToDisplay', computed: true, i18nNameKey: 'size'
  string 'htmlMobileDescription', computed: true
}

Interface('IAttachmentHolder',
        extension: 'IAttachmentHolderExtension',
        processor: 'IAttachmentHolderProcessor',
        services: ['org.jspresso.framework.model.component.IComponent': null]) {

  set 'attachments', ref:'Attachment', processors: ['AttachmentsProcessor'], ordering: ['createTimestamp': 'DESCENDING']

  // computed
  set 'selectedAttachments', ref:'Attachment', id: 'IAttachmentHolder-selectedAttachments', computed: true, delegateWritable: true
  string 'attachmentsLabel', computed: true
}

