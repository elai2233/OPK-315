// TV OBJECT
var TV = function (li, ri) {
	this.li = li;
	this.ri = ri;
	this.on = true;

	return this;
}

//функция которая принимает импрешшион - массив обьектов типа типа {li: n, ri: m}, по сути массив интервалов
//функция преобразовывает этот массив соединяя интервалы друг с другом и выдает красивый массив интервалов по порядку и без повторов
function merge(imp) {

	var flag = true
	while (flag)
	{
		flag = false;

		for (var i = 0; i < (imp.length - 1); i++){
			for (var j = i + 1; j < imp.length; j++){
				if ((imp[j].li <= imp[i].li) && (imp[j].ri >= (imp[i].li - 1))) {
					if (imp[i].li != imp[j].li) {
						imp[i].li = imp[j].li;
						flag = true;
					}
				}	
				if ((imp[j].ri >= imp[i].ri) && (imp[j].li <= (imp[i].ri + 1))) {
					if (imp[i].ri != imp[j].ri) {
						imp[i].ri = imp[j].ri;
						flag = true;
					}
				}
			}
		}
	}

	for (var i = 0; i < imp.length; i++) {
		for(var j = i + 1; j < imp.length; j++) {
			if ((imp[j].li < imp[i].li) || (imp[j].li === imp[i].li && imp[j].ri > imp[i].ri))
				[imp[i], imp[j]] = [imp[j], imp[i]]; //swap
		}
	}

	for (var i = 0; i < imp.length; i++){
		cycle:
		for (var j = 0; j < imp.length; j++)
		{
			if (i!=j) {
				if (imp[i].li >= imp[j].li && imp[i].ri <= imp[j].ri)
				{
					imp.splice(i, 1);
					i--;
					break cycle;
				}
			}
		}
	}
	return imp;
}

function makeImpression(tvs) {
	var impression = [];
	tvs.forEach(function (item) {
		if (item.on)
			impression.push({li: item.li, ri: item.ri});
	});
	return impression;
}

function isEqual(imp1, imp2) {
	if (imp1.length != imp2.length)
		return false;
	for (var i = 0; i < imp1.length; i++)
	{
		if ((imp1[i].li != imp2[i].li) || (imp1[i].ri != imp2[i].ri))
			return false;
	}
	return true;
}
var TVs = []; // массив телевизоров
var n = +prompt("Enter a number of TVs"); //ввод количества телевизоров, 
//+ перем промтом - явное преобразование в числовое значение. нужно число, а промпт возвращает строку
console.log(n);
var Li, Ri;
for (var i = 0; i < n; i++) {
	Li = +prompt("Enter li for TV #" + (i + 1));
	Ri = +prompt("Enter ri for TV #" + (i + 1));
	console.log(Li + " " + Ri);
	TVs.push(new TV(Li, Ri));
}

var impression = merge(makeImpression(TVs)); //создание массива интервалов
var checking;
var noAnswer = true;
//цикл по очереди выключает каждый телевизор, и проверяем изменится ли интервал
cycle:
for (var i = 0; i < TVs.length; i++)
{
	TVs[i].on = false;
	checking = merge(makeImpression(TVs));
	if (isEqual(impression, checking)) {
		console.log("ANSWER: " + (i+1));
		noAnswer = false;
		//break cycle; если нужен первый попавшийся, убери комментарий с брейк цайкл
	}
	TVs[i].on = true;
}
if (noAnswer) console.log("-1");
