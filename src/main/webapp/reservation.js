/**
 * 
 */
 function resClick(i,j) {
	var name = prompt("请输入教师姓名：");
	document.getElementById(i+"name"+j).value = name;
}
function delClick(i,j) {
	document.getElementById(i+"name"+j).value = document.getElementById(i+" "+j).value;
	if (confirm("确认是否删除此预约？")) {
		document.getElementById(i+"confirm"+j).value = 1;
	}
	else {
		document.getElementById(i+"confirm"+j).value = 0;
	}
	
}

$.fn.countTo = function (options) {
    options = options || {};
    return $(this).each(function () {
        var settings = $.extend({}, $.fn.countTo.defaults, {
            from:            $(this).data('from'),
            to:              $(this).data('to'),
            speed:           $(this).data('speed'),
            refreshInterval: $(this).data('refresh-interval'),
            decimals:        $(this).data('decimals')
        }, options);
        var loops = Math.ceil(settings.speed / settings.refreshInterval),
            increment = (settings.to - settings.from) / loops;
        var self = this,
            $self = $(this),
            loopCount = 0,
            value = settings.from,
            data = $self.data('countTo') || {};

        $self.data('countTo', data);

        if (data.interval) {
            clearInterval(data.interval);
        }
        data.interval = setInterval(updateTimer, settings.refreshInterval);

        render(value);

        function updateTimer() {
            value += increment;
            loopCount++;
            render(value);

            if (typeof(settings.onUpdate) == 'function') {
                settings.onUpdate.call(self, value);
            }

            if (loopCount >= loops) {
                $self.removeData('countTo');
                clearInterval(data.interval);
                value = settings.to;

                if (typeof(settings.onComplete) == 'function') {
                    settings.onComplete.call(self, value);
                }
            }
        }

        function render(value) {
            var formattedValue = settings.formatter.call(self, value, settings);
            $self.html(formattedValue);
        }
    });
};

$.fn.countTo.defaults = {
    from: 0, 
    to: 0, 
    speed: 1, 
    refreshInterval: 0.5, 
    decimals: 0,  
    formatter: formatter, 
    onUpdate: null, 
    onComplete: null 
};

function formatter(value, settings) {
    return value.toFixed(settings.decimals);
}

$('#count-number').data('', {
    formatter: function (value, options) {
        return value.toFixed(options.decimals).replace(/\B(?=(?:\d{3})+(?!\d))/g, ' ');
    }
});

$(function() {
	$('.timer').each(count);
    $('.count-title').removeClass('timer');
 });

function count(options) {
    var $this = $(this);
    options = $.extend({}, options || {}, $this.data('countToOptions') || {});
    $this.countTo(options);
}