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
			 <div class="row">
		        <div class="col py-2">
		            <div class="card">
		                <div class="card-body">
		                    <div class="float-right text-muted">Mon, Jan 9th 2019 7:00 AM</div>
		                    <h4 class="card-title text-muted">Day 1 Orientation</h4>
		                    <p class="card-text">Welcome to the campus, introduction and get started with the tour.</p>
		                </div>
		            </div>
		        </div>
		    </div>				
		);
	}
}

class Timeline extends React.Component {
	render() {
		return (
		  <div>
			<Header/>
			<Response/>
			<Response/>
		  </div>
		);
	}
}

const domContainer = document.querySelector('#react');
ReactDOM.render(React.createElement(Timeline), domContainer);