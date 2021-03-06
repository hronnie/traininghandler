<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<ul id="menu" class="clearfix">
    <li><a href="#">Home</a></li>
    <li><a href="#">About</a>
      <ul>
        <li><a href="#">History</a></li>
        <li><a href="#">The Team</a></li>
        <li><a href="#">Our Mission</a></li>
      </ul>
    </li>
    <li><a href="#">Categories</a>
      <ul>
        <li class="purple"><a href="#">Design</a>
          <ul>
            <li><a href="#">Photoshop</a></li>
            <li><a href="#">Illustrator</a></li>
            <li><a href="#">InDesign</a></li>
          </ul>
        </li>
        <li class="green"><a href="#">Writing</a>
          <ul>
            <li><a href="#">Copywriting</a></li>
            <li><a href="#">Journalism</a></li>
            <li><a href="#">Poetry</a></li>
            <li><a href="#">Storytelling</a></li>
          </ul>
        </li>
        <li class="aqua"><a href="#">Accounting</a>
          <ul>
            <li><a href="#">Taxes</a></li>
            <li><a href="#">Credit</a></li>
            <li><a href="#">Asset Management</a></li>
          </ul>
        </li>
        <li class="red"><a href="#">Marketing</a>
          <ul>
            <li><a href="#">Print</a></li>
            <li><a href="#">Digital</a></li>
            <li><a href="#">Branding</a></li>
            <li><a href="#">Presenting</a></li>
            <li><a href="#">Social Media</a></li>
          </ul>
        </li>
        <li class="blue"><a href="#">Development</a>
          <ul>
            <li><a href="#">HTML5/CSS3</a></li>
            <li><a href="#">jQuery</a></li>
            <li><a href="#">PHP</a></li>
            <li><a href="#">Ruby on Rails</a></li>
          </ul>
        </li>
        <li class="gold"><a href="#">Photography</a>
          <ul>
            <li><a href="#">Mechanics</a></li>
            <li><a href="#">Composition</a></li>
          </ul>
        </li>
      </ul>
    </li>
    <li><a href="#">Social</a>
      <ul>
        <li><a href="#">Facebook</a></li>
        <li><a href="#">Twitter</a></li>
        <li><a href="#">YouTube</a></li>
        <li><a href="#">Instagram</a></li>
        <li><a href="#"></a></li>
      </ul>
    </li>
  </ul>		
		
		
		
		
<script type="text/javascript">
$(function(){
  $('a[href="#"]').on('click', function(e){
    e.preventDefault();
  });
  
  $('#menu > li').on('mouseover', function(e){
    $(this).find("ul:first").show();
    $(this).find('> a').addClass('active');
  }).on('mouseout', function(e){
    $(this).find("ul:first").hide();
    $(this).find('> a').removeClass('active');
  });
  
  $('#menu li li').on('mouseover',function(e){
    if($(this).has('ul').length) {
      $(this).parent().addClass('expanded');
    }
    $('ul:first',this).parent().find('> a').addClass('active');
    $('ul:first',this).show();
  }).on('mouseout',function(e){
    $(this).parent().removeClass('expanded');
    $('ul:first',this).parent().find('> a').removeClass('active');
    $('ul:first', this).hide();
  });
});
</script>
		
		