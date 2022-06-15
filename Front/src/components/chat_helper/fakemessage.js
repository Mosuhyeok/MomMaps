export const fakeMessages = [
  {
    type: "text",
    text: "안녕하세요. 맘맵스 입니다, 원하시는 카테고리를 말해주세요..... (메뉴,회원)",
    disableInput: false,
  },
  {
    type: "button",
    text: "어떤 메뉴로 안내해드릴까요?",
    options: [
      {
        text: "공지사항",
        value: "/announcement",
        action: "url",
      },
      {
        text: "게시판",
        value: "/board",
        action: "url",
      },
      {
        text: "지역 검색",
        value: "/house",
        action: "url",
      },
      {
        text: "취소",
        value: "result_not_match",
        action: "postback",
      },
    ],
    disableInput: true,
  },
  {
    type: "button",
    text: "회원가입 및 로그인을 선택해주세요.",
    options: [
      {
        text: "회원가입",
        value: "/regist",
        action: "url",
      },
      {
        text: "로그인",
        value: "/login",
        action: "url",
      },
      {
        text: "취소",
        value: "result_not_match",
        action: "postback",
      },
    ],
    disableInput: true,
  },
];
