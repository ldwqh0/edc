<template>
  <draggable class="dragArea"
             tag="ul"
             :list="tasks"
             draggable=".ab"
             :group="{ name: 'g1' }">
    <span class="ab" v-if="tasks && tasks.length>0">点我拖动</span>
    <li v-for="el in tasks" :key="el.name">
      <p>{{ el.name }}</p>
      <nested-draggable :tasks="el.tasks" />
    </li>
  </draggable>
</template>
<script>
  import draggable from 'vuedraggable'

  export default {
    props: {
      tasks: {
        required: true,
        type: Array
      }
    },
    components: {
      draggable
    },
    name: 'NestedDraggable'
  }
</script>
<style scoped>
  .dragArea {
    min-height: 50px;
    outline: 1px dashed;
  }
</style>
