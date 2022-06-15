import { fakeMessages } from "@/components/chat_helper/fakemessage.js";

export const messageService = {
  createMessage,
};

function createMessage(value) {
  return new Promise((resolve, reject) => {
    console.log(value);
    console.log(reject);
    setTimeout(() => {
      if (value.text == "메뉴") {
        resolve(fakeMessages[1]);
      } else if (value.text == "회원") {
        resolve(fakeMessages[2]);
      } else {
        resolve(fakeMessages[0]);
      }
      // let randomNumber = Math.floor(Math.random() * fakeMessages.length);
      // resolve(fakeMessages[randomNumber]);
    }, 1000);
  });
}
