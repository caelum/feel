class Header extends React.Component {
	render() {
		return (
				<h2 class="text-center text-muted py-3">{this.props.title}</h2>
		);
	}
}

class Response extends React.Component {
	
	render() {
		return (
			 <div class="row" key={this.props.message.id}>
		        <div class="col py-2">
		            <div class="card">
		                <div class="card-body">
		                    <div class="float-right text-muted">{this.props.message.date}</div>
		                    <h4 class="card-title text-muted">{this.props.message.from}</h4>
		                    <p class="card-text">{this.props.message.comment}</p>
		                </div>
		            </div>
		        </div>
		    </div>				
		);
	}
}

class SuccessMessage extends React.Component {
	render() {
		const classes = "alert alert-success alert-dismissible fade "+(this.props.show ? "show" : '');
		return (
				<div className={classes}>

				    <span>{this.props.message}</span>
				    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				        <span aria-hidden="true">&times;</span>
				    </button>

				</div>				
		);
	}
}

class MessageForm extends React.Component {
	
	constructor(props) {
		super();
		this.state = {show : props.show};
	}
	
	send(event){
		event.preventDefault();
		
        const requestInfo = {
                method:'POST',
                body:JSON.stringify({name:this.name.value,comment:this.comment.value}),
                headers: new Headers({
                  'Content-type':'application/json'
                })
              };
        
      fetch('/behavior/anonimous/timeline/messages/'+HASH+'/append',requestInfo)
        .then(response => {
          if(response.ok){        	
        	this.name.value = "";
        	this.comment.value = "";
        	this.setState({show:true});
        	this.props.callback();  
        	setTimeout(
        		      () => this.setState({show:false}),
        		      5000
        		    );        	
            return "";
          } else {
        	alert("Infelizmente não foi possível adicionar o comentário agora. Caso o erro persista, chame Alberto ou Luísa");  
            throw new Error("Ocorreu um problema");
          }
        }); 
    }    
	
	render() {				
		return (
			<div>
				<Header title="Nova mensagem"/>
				<SuccessMessage show={this.state.show} message="Comentario enviado com sucesso"/>	
			    <form method="post" onSubmit={this.send.bind(this)}>

		        <div class="form-group">
		            <input id="name" class="form-control" placeholder="Seu nome(opcional)" ref={input => this.name = input}/>		            
		        </div>

		        <div class="form-group">
		            <textarea required="required" class="form-control" placeholder="Nos conte o que aconteceu da maneira mais detalhada possível." rows="5" ref={input => this.comment = input}></textarea>
		        </div>
		        
		        <div>
		            <button class="btn btn-info btn-block">Enviar</button>
		        </div>
		    </form>	
		   </div>
		);
	}
}


class Timeline extends React.Component {
	
	constructor(){
		super();
		this.state = {messages : []};
	}
	
	listAll() {
		fetch('/behavior/anonimous/timeline/messages/'+HASH)
		.then(response => response.json())
		.then(listOfMessages => {
			this.setState({messages : listOfMessages});
		});		
	}
	
	componentDidMount() {
		this.listAll();		
	}
	
	render() {
		return (
		  <div>
			<Header title="Conversa"/>
			{
				this.state.messages.map(m => {
					return (<Response message={m}/>); 
				})
			}
			
			<hr/>
						
			<MessageForm callback={this.listAll.bind(this)} show={false}/>
		  </div>
		);
	}
}

const domContainer = document.querySelector('#react');
ReactDOM.render(React.createElement(Timeline), domContainer);