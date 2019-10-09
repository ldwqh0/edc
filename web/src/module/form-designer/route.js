export default [{
  path: '/formDesigns',
  component: () => import('@/components/templates/LeftTemplate'),
  children: [{
    name: 'forms',
    path: '',
    component: () => import('./component')
  }, {
    name: 'design',
    path: ':id',
    props: true,
    component: () => import('./component/FormDesigner')
  }]
}]
