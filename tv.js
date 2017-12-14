let period_arr = new Array(11);
for(let i=0;i<period_arr.length;i++)
{
    period_arr[i]=0;
    console.log(period_arr[i]+"num"+i);
}
function get_input_value(id_input) {
    return document.getElementById(id_input).value;
}
function check_value(id_input) {
    let input_value = get_input_value(id_input);
    if(input_value>2105 || input_value<1)
    {
        alert("Введите корректные данные");
        return false;
    } else return true;
}
class TeleVisor{
    set_ri(){
        while (this.li >= this.ri){
        this.ri = Math.random() * (11);
        }
    return this.ri=Math.floor(this.ri);
    };
    constructor(num){
     this.num = num;
     this.li = Math.floor(Math.random() * (11));
     this.ri = 0;
     this.lenght_per=0;
    }
    get period_li(){
        return this.li;
    }
    get period_ri(){
        return this.ri;
    }
    get num_g(){
        return this.num;
    }
    show_all_info(){
        console.log("телевизор "+this.num_g+"преиод с "+this.period_li+"по"+this.period_ri)
    }
}
let TV=[];
function fill_period_arr(obj) {
    for(let i = obj.period_li;i<=obj.period_ri;i++)
    {
        period_arr[i]+=1;
    }
}
function generate_period(id_input){
    if(check_value(id_input)){
    document.getElementById("check").disabled = true;
       for(i=1;i<=get_input_value(id_input);i++)
       {
            TV[i] = new TeleVisor(i);
            TV[i].set_ri();
            TV[i].show_all_info();
            fill_period_arr(TV[i]);
           let div_usually = document.createElement('div');
           div_usually.id = "tv_div_" + TV[i].num_g;
           document.getElementById("all_tv").appendChild(div_usually);
            let div = document.createElement('div');
            div.id = "tv_" + TV[i].num_g;
            div.innerHTML = "<img src='img/tv.png'> <h3>ТВ"+div.id.slice(3)+"</h3>";
            document.getElementById("tv_div_" + TV[i].num_g).appendChild(div);
            let headers = document.createElement("h1");
            headers.id = "idtv_" + i;
            headers.innerText = "С " + TV[i].period_li +" по "+ TV[i].period_ri;
           document.getElementById("tv_div_" + TV[i].num_g).appendChild(headers);
       }
    }
}

function dell_from_period_arr(obj) {

    for(let g=0;g<period_arr.length;g++)
    {
        console.log("число "+period_arr[g]+" с индексом "+g);
    }
    console.log("---------------------");
    let flag = false;
    for(let i = obj.period_li;i<=obj.period_ri;i++)
    {
        period_arr[i]--;
        if(period_arr[i]===0){
            flag=true;
            document.getElementById("tv_"+obj.num_g).className="red";
        }else if( document.getElementById("tv_"+obj.num_g).className!=="red"){
            document.getElementById("tv_"+obj.num_g).className="green";
        }
    }
    if(flag){
        for(let v = obj.period_li;v<=obj.period_ri;v++){
            period_arr[v]++;
        }
    }

}
    function check_TeleVisors(id_input){
    document.getElementById("check_tv").disabled = true;
        for(let z=1;z<=get_input_value(id_input);z++)
        {
            dell_from_period_arr(TV[z]);
        }
    }