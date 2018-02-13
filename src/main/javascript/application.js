// HASHER
hasher.initialized.add(function(h) {
	crossroads.parse(h);
});
hasher.changed.add(function(h) {
	crossroads.parse(h);
});

// NAMESPACE
ko.TournamentCommunity = {
	vm : {}
};

// INIT APP
$(function() {
	hasher.init();
});
