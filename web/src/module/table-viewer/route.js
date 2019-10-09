export default [{
  path: '/datas',
  component: () => import('@/components/templates/LeftTemplate'),
  children: [{
    name: 'dataList',
    path: ':tableId',
    props: true,
    component: () => import('./component/DataList')
  }]
}]
