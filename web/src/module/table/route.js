export default [{
  path: '/tables',
  component: () => import('./component/Template'),
  children: [{
    name: 'table',
    path: ':id',
    props: true,
    component: () => import('./component/Table')
  }]
}]
