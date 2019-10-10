export default [{
  path: '/datas',
  component: () => import('@/components/templates/HeaderTemplate'),
  children: [{
    path: '',
    component: () => import('./component/TableList'),
    children: [{
      name: 'dataList',
      path: ':tableId',
      props: true,
      component: () => import('./component/DataList')
    }, {
      name: 'dataForm',
      path: ':tableId/:dataId',
      props: true,
      component: () => import('./component/EdcForm')
    }]
  }]
}]
