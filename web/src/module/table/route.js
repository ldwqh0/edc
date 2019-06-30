export default [{
  path: '/tables',
  component: () => import('@/components/templates/HeaderTemplate'),
  children: [{
    name: 'tables',
    path: '',
    component: () => import('./component/Tables'),
    children: [{
      name: 'table',
      path: ':id',
      props: true,
      component: () => import('./component/Table')
    }]
  }]
}]
