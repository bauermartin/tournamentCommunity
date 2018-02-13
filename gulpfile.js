var gulp = require('gulp');
var concat = require('gulp-concat');
var gulpif = require('gulp-if');
var jshint = require('gulp-jshint');
var uglify = require('gulp-uglify');
var sass = require('gulp-sass');

var dist = false;

gulp.task('build_js', function() {
	lint(
			[ 'src/main/javascript/application.js']).pipe(
			concat('application.min.js')).pipe(uglify({
		mangle : false,
		compress : false,
		output : {
			beautify : true,
			indent_level : 2
		}
	})).pipe(gulpif(dist, uglify())).pipe(gulp.dest('src/main/webapp/js'));
});

gulp.task('build_css', function() {
	return gulp.src([ 'src/main/scss/*.scss' ]).pipe(sass()).pipe(
			gulp.dest("src/main/webapp/css"));
});

gulp.task('build_sass_ext', function() {
	return gulp.src([ 'node_modules/bootstrap/scss/bootstrap.scss' ]).pipe(
			sass()).pipe(gulp.dest("src/main/webapp/css/ext"));
});

gulp.task('build_js_ext', function() {
	return gulp.src(
			[ 'node_modules/bootstrap/dist/js/bootstrap.min.js',
					'node_modules/jquery/dist/jquery.min.js',
					'node_modules/popper.js/dist/umd/popper.min.js',
					'node_modules/crossroads/dist/crossroads.min.js',
					'node_modules/signals/dist/signals.min.js',
					'node_modules/hasher/dist/js/hasher.min.js',
					'src/main/javascript/ext/knockout-3.4.2.js' ]).pipe(
			gulp.dest("src/main/webapp/js/ext"));
});

gulp.task('default', [ 'build_js', 'build_js_ext', 'build_css', 'build_sass_ext' ]);

function lint(src) {
	var streams = src;
	if (typeof src === 'string' || Array.isArray(src)) {
		// noinspection JSUnresolvedFunction
		streams = gulp.src(src);
	}
	// noinspection JSUnresolvedFunction
	return streams.pipe(jshint('.jshintrc')).pipe(jshint.reporter('default'));
}