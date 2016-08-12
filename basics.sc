s.boot;

(
{
	SinOsc.ar(840, 0, SinOsc.kr(2, 0, 1));
}.play;
)

(
SynthDef("harc", {|freq=440, mul=0.1, out=0|
  var sig = SinOsc.ar(freq, 0, mul);
  Out.ar(out, sig.dup);
}).add;
)

(
{
	SinOsc.ar(840, 0, SinOsc.kr(2, 0, 1));
}.play;
)

~synth = Synth(\harc, [\freq, 340, \mul, 0.2]);

~synths = 20.collect {|i| ~synth = Synth(\harc, [\freq, rrand(220, 880), \mul, 0.03]);}

~synths2 = 20.collect {|i| ~synth = Synth(\harc, [\freq, rrand(220, 880), \mul, i.linlin(0, n, 0.1, 0.01)]);}