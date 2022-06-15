<template lang="pug">
.qkb-board-content(ref="boardContent")
  .qkb-board-content__bubbles(
    ref="boardBubbles"
  )
    message-bubble(
      v-for="(item, index) in mainData",
      :key="index",
      :message="item",
    )
    .qkb-board-content__bot-typing(v-if="botTyping")
      slot(name="botTyping")
        message-typing
</template>

<script>
import MessageBubble from "@/components/chat/MessageBubble/Main.vue";
import MessageTyping from "@/components/chat/MessageBubble/Typing.vue";
export default {
  components: {
    MessageBubble,
    MessageTyping,
  },
  props: {
    mainData: {
      type: Array,
      required: true,
    },
    botTyping: {
      type: Boolean,
      default: false,
    },
  },
  mounted() {
    this.updateScroll();
  },
  watch: {
    mainData: function (newVal) {
      console.log(newVal);
      this.$nextTick(() => {
        this.updateScroll();
      });
    },
  },
  methods: {
    updateScroll() {
      const contentElm = this.$refs.boardContent;
      const offsetHeight = this.$refs.boardBubbles.offsetHeight;
      contentElm.scrollTop = offsetHeight;
    },
  },
};
</script>
