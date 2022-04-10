// $(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
$(".answerWrite input[type=submit]").click(addAnswer);
//$(".deleteAnswer input[type=submit]").click(deleteAnswer);

function addAnswer(e) {
  e.preventDefault();

  var queryString = $("form[name=answer]").serialize();
  $.ajax({

    type : 'post',
    url : '/api/qna/addAnswer',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : function(json, status){
      answerList(json.questionId);
    },
  });
}

function deleteAnswer(e, answerId, questionId) {
  e.preventDefault();
    // questionId 가져와서 넘기기
  var queryString = 'answerId=' + answerId + '&questionId=' + questionId;
    console.log(queryString)
  $.ajax({
    type : 'post',
    url : '/api/qna/deleteAnswer',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : function(json, status){
      answerList(json.questionId);
    },
  });
}

function answerList(questionId){
    var json = new Object();
    json.questionId = questionId;
    var queryString = 'questionId=' + questionId;

    // 여기서 새로 추가한 댓글이 이상하게 나옴
    // 그리고 삭제 후에도 이 함수 타도록 수정해야 됨
  $.ajax({
    type : 'post',
    url : '/api/qna/answerList',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : function(json, status){
        var template = "";
        var answers = json.answers;
        var answerTemplate = $("#answerTemplate").html();

        for(var i = 0; i < answers.length; i++){
            template += answerTemplate
                .format(answers[i].writer, new Date(answers[i].createdDate)
                    , answers[i].contents, answers[i].answerId, answers[i].questionId);
                    console.log(answers[i].questionId)
        }

        $("#countOfComment").html(json.countOfComment);
        $(".qna-comment-slipp-articles").html(template);
    },
  });
}
/*
function onSuccess(json, status){
  var questionId = json.questionId;
  answerList(questionId);
  var answer = json.answer;
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId, answer.answerId);

  $("#countOfComment").html(json.countOfComment);
  $(".qna-comment-slipp-articles").prepend(template);
}
*/
function onError(xhr, status) {
  alert("error");
}

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};