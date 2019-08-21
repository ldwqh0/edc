<template>
  <div>
    dd
    <nested-draggable :tasks="list" />
  </div>
</template>

<script>
  import Vue from 'vue'
  import { Component } from 'vue-property-decorator'
  import NestedDraggable from './infra/nested'

  @Component({
    components: {
      NestedDraggable: NestedDraggable
    }
  })
  export default class A extends Vue {
    list = [{
      name: 'task 1',
      tasks: [{
        name: 'task 2',
        tasks: []
      }]
    }, {
      name: 'task 3',
      tasks: [{
        name: 'task 4',
        tasks: []
      }]
    }, {
      name: 'task 5',
      tasks: []
    }]
  }

</script>

<style scoped>

</style>
