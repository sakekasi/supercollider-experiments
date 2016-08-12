(
SynthDef("harc", {|freq=440, mul=0.1, out=0|
  var sig = SinOsc.ar(freq, 0, mul);
  var env = EnvGen.ar(
    Env.perc(0.001, 0.5) //([0, 1, 0.5, 0], [1.5, 0.5, 2], [\lin,\exp,\sin]), doneAction:2
  );

  Out.ar(out, (sig * env).dup );
}).add;
)

~synth = Synth(\harc, [\freq, 80.midicps, \mul, 0.2]);

Env.asr.test

Env.perc.test

Env.triangle.plot.test

Env([0, 1, 0.5, 0, 0.7, 0.5, 0.25, 0], [1.5, 0.5, 2, 1, 1, 1, 1], [\lin,\exp,\sin, \exp,\exp,\exp,\exp]).test.plot

({
  Scale.majorPentatonic.degrees.do {|degree|
    var freq = (60 + degree).midicps;
    Synth(\harc, [\mul, 0.2, \freq, freq]);
    0.125.wait;
  };
  Scale.melodicMinor.degrees.reverse.do {|degree|
    var freq = (60 + degree).midicps;
    Synth(\harc, [\mul, 0.2, \freq, freq]);
    0.125.wait;
  };
}.fork
)

Scale.directory

// TempoClock
// SystemClock
// AppClock

TempoClock.default.tempo = 100 / 60;

(
Tdef(\forever, {
  inf.do {
    Scale.majorPentatonic.degrees.do {|degree|
      var freq = (60 + degree).midicps;
      Synth(\harc, [\mul, 0.2, \freq, freq]);
      0.125.wait;
    };
    Scale.melodicMinor.degrees.reverse.do {|degree|
      var freq = (60 + degree).midicps;
      Synth(\harc, [\mul, 0.2, \freq, freq]);
      0.125.wait;
    };
  }
}).play;
)


(
Tdef(\forever, {
  inf.do { |i|
    var strength = (i%4==0).if({0.4}, {0.2});

    Scale.mixolydian.degrees.[(0..2)].do{|degree|
      var freq = (60 + degree).midicps;
      Synth(\harc, [\mul, strength, \freq, freq]);
    };
    0.4.wait;
  }
}).play;
)

s.boot