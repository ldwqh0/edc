export default [{
  path: '/formDesigns',
  component: () => import('@/components/templates/LeftTemplate'),
  children: [{
    name: 'design',
    path: '',
    props: true,
    component: () => import('./component/FormDesigner')
  }]
}]
