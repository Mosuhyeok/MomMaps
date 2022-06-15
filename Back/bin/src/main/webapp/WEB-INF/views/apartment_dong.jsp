<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>매매가 검색</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <style type="text/css">
      table {
        width: 480px;
        height: 100px;
      }
      th,
      td {
        text-align: center;
      }
    </style>
  </head>
  <body>
  <%@ include file="./include/header.jsp" %>
  <br><br><br><br>
    <div>

      <div class="container-drop p-5 bg-dark">
        <select class="drop-child" name="sido" id="sido"></select>
        <select class="drop-child" name="gugun" id="gugun">
          <option value="">시/구/군</option>
        </select>
        <select class="drop-child" name="dong" id="dong">
          <option value="">동</option>
        </select>
      </div>
      <div class="">
        <h3>거래 정보</h3>
        <div style="float: left; width: 30%; height: 500px; overflow: auto">
          <table>
            <tr>
              <th>아파트</th>
              <th>법정동</th>
              <th>전용면적</th>
              <th>거래금액</th>
            </tr>
            <tbody id="aptinfo"></tbody>
          </table>
        </div>
        <div id="map" style="float: left; width: 70%; height: 500px"></div>
      </div>
    </div>

	<%@ include file="./include/footer.jsp" %>
  </body>
</html>
