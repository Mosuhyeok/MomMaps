<template>
  <VueBotUI :messages="messageData" :options="botOptions" :bot-typing="botTyping" :is-open="isopen" :input-disable="inputDisable" @msg-send="msgSend" @init="botStart" />
  <!-- <BotUI :messages="messageData" :options="botOptions" :bot-typing="botTyping" :is-open="isopen" :input-disable="inputDisable" @msg-send="msgSend" @init="botStart" /> -->
</template>

<script>
import { VueBotUI } from "vue-bot-ui";
// import { BotUI } from "@/components/chat/BotUI.vue";
import { messageService } from "@/components/chat_helper/message.js";

export default {
  name: "chatbot",
  data() {
    return {
      messageData: [], // See Data example below
      botTyping: false,
      inputDisable: false,
      isopen: true,
      botOptions: {
        // See the list of options below
        botTitle: "MoMAPS",
        colorScheme: "#367E26",
        //textColor: "#CEFBC9", //헤더글자, 아이콘색
        //bubbleBtnSize: 76,
        //boardContentBg: "#CEFBC9",  //챗봇 채팅창색
      },
    };
  },
  methods: {
    botStart() {
      // Get token if you want to build a private bot
      // Request first message here
      // Fake typing for the first message
      this.botTyping = true;
      setTimeout(() => {
        this.botTyping = false;
        this.messageData.push({
          agent: "bot",
          type: "text",
          text: "안녕하세요. 맘맵스 입니다, 원하시는 카테고리를 말해주세요..... (메뉴,회원)",
        });
      }, 1000);
    },
    messageSendHandler() {},
    msgSend(value) {
      // Push the user's message to board
      this.messageData.push({
        agent: "user",
        type: "text",
        text: value.text,
      });
      console.log(value);
      this.getResponse(value);
    },
    getResponse(value) {
      // Loading
      this.botTyping = true;
      // Post the message from user here
      // Then get the response as below
      // Create new message from fake data
      messageService.createMessage(value).then((response) => {
        const replyMessage = {
          agent: "bot",
          ...response,
        };
        this.inputDisable = response.disableInput;
        this.messageData.push(replyMessage);
        // finish
        this.botTyping = false;
      });
    },
  },
  components: {
    VueBotUI,
    //BotUI,
  },
};
</script>

<style></style>
