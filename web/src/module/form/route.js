export default [{
  path: '/forms',
  component: () => import('@/components/templates/LeftTemplate'),
  children: [{
    name: 'form',
    path: ':id',
    props: true,
    component: () => import('./component/EdcForm')
  }]
}]
