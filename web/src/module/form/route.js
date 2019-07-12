export default [{
  path: '/forms',
  component: () => import('@/components/templates/LeftTemplate'),
  children: [{
    name: 'dataList',
    path: ':tableId',
    props: true,
    component: () => import('./component/DataList')
  }, {
    name: 'form',
    path: ':tableId/:dataId',
    props: true,
    component: () => import('./component/EdcForm')
  }]
}]
