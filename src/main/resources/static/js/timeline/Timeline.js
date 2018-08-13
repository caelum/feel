class Header extends React.Component {
	render() {
		return (
				<h2 class="text-center text-muted py-3">Conversa</h2>
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

class Timeline extends React.Component {
	
	constructor(){
		super();
		this.state = {messages : []};
	}
	
	componentDidMount() {
		fetch('/behavior/anonimous/timeline/messages/'+HASH)
			.then(response => response.json())
			.then(listOfMessages => {
				this.setState({messages : listOfMessages});
			});
		
	}
	
	render() {
		return (
		  <div>
			<Header/>
			{
				this.state.messages.map(m => {
					return (<Response message={m}/>); 
				})
			}
		  </div>
		);
	}
}

const domContainer = document.querySelector('#react');
ReactDOM.render(React.createElement(Timeline), domContainer);