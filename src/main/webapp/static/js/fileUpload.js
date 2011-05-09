$(document).ready(function() {
  function submitFormToIframe($form, target) {
    $form
      .attr('target', target)
      .removeAttr('onsubmit')
      .attr('action', '/ajax_request/' + lift_page)
      .attr('method', 'post')
      .attr('enctype', 'multipart/form-data' )    
      .attr('encoding', 'multipart/form-data')
      .find('input:submit,button[type=submit]')
        .removeAttr('onclick')
      .end()
      .append($('<input type="hidden" name="' +
                  $form.find('input:submit').attr('name') +
                  '" value="_" />'))
      .after(
        // do not use attr() to set name. IE7 will hate this: http://stackoverflow.com/questions/2105815/weird-behaviour-of-iframe-name-attribute-set-by-jquery-in-ie
        $('<iframe id="' + target + '" name="' + target + '" />')
          .addClass('form-target')
          .css('display','none')
          .load(function() {
              $.globalEval($(this).contents().text());
          })
      );
  }

  submitFormToIframe($('form:has(input[type=file])'), 'fileUploadExampleForm');
});
